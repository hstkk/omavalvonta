package models.dynamicforms;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import javax.validation.constraints.NotNull;
import org.hibernate.envers.Audited;
import play.data.validation.Constraints.Required;
import models.Batch;
import models.Product;
import models.helpers.Dao;
import models.helpers.Model;

@Entity
@Audited
@AttributeOverride(name = "lastModified", column = @Column(name = "created"))
public class Results extends Model {
	public final static Dao<Results, Long> dao = new Dao<Results, Long>(
			Results.class);

	@Required
	@ManyToOne
	@NotNull
	public Form form;

	@Required
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false)
	@NotNull
	public List<Batch> batches = new ArrayList<Batch>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "results_id", nullable = false)
	@NotNull
	public List<Result> results = new ArrayList<Result>();

	@Transient
	Product product;

	@Override
	public void onCreate() {
		super.onCreate();
		if (this.form != null)
			this.form = Form.dao.findById(this.form.id);
		this.batches = Batch.dao.getReference(this.batches);
		fill();
	}

	@Override
	public void onUpdate() {
	}

	private void fill() {
		if (this.form != null && this.form.fieldsets != null)
			for (Fieldset fieldset : this.form.fieldsets)
				for (Field field : fieldset.fields) {
					Result result = new Result(field);
					this.results.add(result);
				}
	}

	public boolean update() {
		Map<Long, Result> resultMap = new LinkedHashMap<Long, Result>();
		Results that = dao.findById(this.id);
		if (that != null) {
			for (Result result : this.results)
				resultMap.put(result.id, result);
			// TODO update that.results
			return dao.update(that);
		}
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
}
