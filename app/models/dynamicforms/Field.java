package models.dynamicforms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import models.User;
import models.helpers.Dao;
import models.helpers.UserModel;
import org.hibernate.envers.Audited;
import play.data.validation.Constraints.*;
import play.i18n.Messages;

@Entity
@Audited
public class Field extends UserModel {
	public final static Dao<Field, Long> dao = new Dao<Field, Long>(Field.class);

	@Required
	@NotNull
	public String name;

	@Lob
	public String help;

	@Required
	@NotNull
	public boolean isRequired;

	@Required
	@NotNull
	@Valid
	@ManyToOne
	@JoinColumn(name = "id", insertable = false, updatable = false, nullable = false)
	public Fieldset fieldset;

	@Transient
	public FieldType fieldTypeEnum;

	@Required
	@NotNull
	public Integer fieldType;

	@Required
	@NotNull
	public boolean isSigned;

	public Double min;

	public Double max;

	public Boolean targetValue;

	public String toString() {
		return name;
	}

	@PrePersist
	@PreUpdate
	private void onPre() {
		if (fieldTypeEnum != null)
			fieldType = fieldTypeEnum.getValue();
	}

	@PostLoad
	private void intToEnum() {
		if (fieldType != null)
			fieldTypeEnum = FieldType.setValue(this.fieldType);
	}

	public static Map<String, String> targetOptions() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("true", Messages.get("boolean.true"));
		map.put("false", Messages.get("boolean.false"));
		return map;
	}

	public static List<Field> findByFieldset(String fieldsetId) {
		try {
			Long id = Long.parseLong(fieldsetId);
			Fieldset fieldset = Fieldset.dao.findById(id);
			if (fieldset != null) {
				CriteriaBuilder criteriaBuilder = dao.getCriteriaBuilder();
				CriteriaQuery<Field> query = criteriaBuilder
						.createQuery(Field.class);
				Root<Field> root = query.from(Field.class);
				query.where(criteriaBuilder.equal(root.get(Field_.fieldset),
						fieldset));
				query.orderBy(criteriaBuilder.asc(root.get("position")));
				return dao.findAllBy(query);
			}
		} catch (Exception e) {
		}
		return null;
	}

	public static Map<String, String> options(String fieldsetId) {
		List<Field> fields = findByFieldset(fieldsetId);
		Map<String, String> options = dao.options();
		if (fields != null && !fields.isEmpty()) {
			Map<String, String> map = new HashMap<String, String>();
			for (Field field : fields) {
				String id = field.id.toString();
				map.put(id, field.toString());
				options.remove(id);
			}
			map.putAll(options);
			return map;
		}
		return options;
	}
}