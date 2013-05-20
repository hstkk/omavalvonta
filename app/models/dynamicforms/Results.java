package models.dynamicforms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.envers.Audited;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import controllers.shib.Session;

import play.data.validation.Validation;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.Required;
import play.i18n.Messages;
import models.Batch;
import models.Product;
import models.Term;
import models.User;
import models.helpers.Dao;
import models.helpers.Model;

@Entity
@Audited
@AttributeOverride(name = "lastModified", column = @Column(name = "created"))
public class Results extends Model {
	public interface Step2 {
	}

	public interface Step3 {
	}

	public interface Update {
	}

	public final static Dao<Results, Long> dao = new Dao<Results, Long>(
			Results.class);

	@Required(groups = { Step2.class, Step3.class })
	@ManyToOne
	@NotNull(message = "", groups = { Step2.class, Step3.class })
	public Form form;

	@Required(groups = { Step3.class })
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false)
	@NotNull(message = "", groups = { Step3.class })
	public List<Batch> batches = new ArrayList<Batch>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "results_id", nullable = false)
	@NotNull(message = "", groups = { Update.class })
	@Valid
	public List<Result> results = new ArrayList<Result>();

	@Override
	public boolean onCreate() {
		if (this.form != null)
			this.form = Form.dao.findById(this.form.id);
		this.batches = Batch.dao.getReference(this.batches);
		fill();
		return super.onCreate();
	}

	@Override
	public boolean onUpdate() {
		return updateResults();
	}

	private void fill() {
		if (this.form != null && this.form.fieldsets != null)
			for (Fieldset fieldset : this.form.fieldsets)
				for (Field field : fieldset.fields) {
					Result result = new Result(field);
					this.results.add(result);
				}
	}

	public boolean updateResults() {
		Results that = dao.findById(this.id);
		List<Result> _newResults = this.results;
		if (that != null) {
			Map<Long, Result> newResults = new HashMap<Long, Result>();
			for (Result result : this.results)
				newResults.put(result.id, result);

			try {
				BeanUtils.copyProperties(that, this);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

			User user = Session.user();

			List<Result> _results = this.results;
			this.results = new ArrayList<Result>();
			for (Result result : _results) {
				Result newResult = newResults.get(result.id);
				if (newResult != null) {
					if (newResult.ack != null && newResult.ack)
						result.user = user;
					else
						result.user = null;
					if (result.isEmpty()
							|| (newResult.reason != null && newResult.reason.id != null)) {
						result.comment = newResult.comment;
						result.valueBoolean = newResult.valueBoolean;
						result.valueDate = newResult.valueDate;
						result.valueDouble = newResult.valueDouble;
						result.valueInt = newResult.valueInt;
						result.valueString = newResult.valueString;
						if (newResult.reason != null
								&& newResult.reason.id != null) {
							result.reason = Term.dao
									.getReference(newResult.reason);
							if (result.reason == null) {
								this.results = _newResults;
								return false;
							}
						}
					}
				}
				this.results.add(result);
			}
			return true;
		}
		this.results = _newResults;
		return false;
	}

	public static play.data.Form<Results> getEmptyForm() {
		play.data.Form<Results> form = new play.data.Form<Results>(
				Results.class);
		return form;
	}

	@Transient
	private Form oldForm;

	public Form getOldForm() {
		if (oldForm == null) {
			oldForm = Form.dao.getVersion(this.form.id, this.lastModified);
			if (oldForm == null)
				oldForm = this.form;
		}
		return oldForm;
	}

	@Transient
	private Map<String, Field> fieldMap;

	public Field getField(String key) {
		if (fieldMap == null || fieldMap.isEmpty()) {
			fieldMap = new LinkedHashMap<String, Field>();
			Form _form = getOldForm();
			if (_form != null && _form.fieldsets != null)
				for (Fieldset fieldset : _form.fieldsets)
					if (fieldset.fields != null)
						for (Field field : fieldset.fields)
							fieldMap.put(field.id.toString(), field);
		}
		return fieldMap.get(key);
	}

	@Transient
	private Map<String, Fieldset> fieldsetMap;

	public Fieldset getFieldset(Long id) {
		return getFieldset(id.toString());
	}

	public Fieldset getFieldset(String key) {
		if (fieldsetMap == null || fieldsetMap.isEmpty()) {
			fieldsetMap = new LinkedHashMap<String, Fieldset>();
			Form _form = getOldForm();
			if (_form != null && _form.fieldsets != null)
				for (Fieldset fieldset : _form.fieldsets)
					fieldsetMap.put(fieldset.id.toString(), fieldset);
		}
		return fieldsetMap.get(key);
	}

	public Product getProduct() {
		if (this.batches != null && !this.batches.isEmpty())
			return this.batches.get(0).product;
		return null;
	}

	@Transient
	private List<String> printedFieldsets = new ArrayList<String>();

	public boolean isFieldsetPrinted(Long id) {
		return isFieldsetPrinted(id.toString());
	}

	public boolean isFieldsetPrinted(String key) {
		boolean printed = printedFieldsets.contains(key);
		if (!printed)
			printedFieldsets.add(key);
		return printed;
	}

	private Map<String, List<ValidationError>> _validate() {
		Map<String, List<ValidationError>> errors = new HashMap<String, List<ValidationError>>();
		SpringValidatorAdapter validator = new SpringValidatorAdapter(
				Validation.getValidator());
		Set<ConstraintViolation<Results>> violations = validator.validate(this);
		for (ConstraintViolation<Results> violation : violations) {
			String field = violation.getPropertyPath().toString();
			String error = violation.getMessage();
			List<ValidationError> list = new ArrayList<ValidationError>();
			list.add(new ValidationError(field, error));
			errors.put(field, list);
		}
		return errors;
	}

	public Map<String, List<ValidationError>> validate() {
		Map<String, List<ValidationError>> errors = _validate();
		if (this.results != null && !this.results.isEmpty()) {
			Results t = dao.findById(this.id);
			if (t != null) {
				Map<Long, Result> oldResults = new HashMap<Long, Result>();
				for (Result result : t.results)
					oldResults.put(result.id, result);
				int i = 0;
				for (Result result : this.results) {
					Result oldResult = oldResults.get(result.id);
					if (oldResult != null
							&& !oldResult.isEmpty()
							&& !oldResult.toString().equals(result.toString())
							&& (result.reason == null || result.reason.id == null)) {
						List<ValidationError> list = new ArrayList<ValidationError>();
						list.add(new ValidationError("required", Messages
								.get("constraint.required")));
						errors.put("results[" + i + "].reason.id", list);
					}
					i++;
				}
			} else
				return errors;
		}
		if (!errors.isEmpty())
			return errors;
		return null;
	}
}
