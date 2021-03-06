package com.me.callme.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Preference generated by hbm2java
 */
@Entity
@Table(name = "pk_sequences")
public class PkSequences  implements java.io.Serializable {

	private static final long serialVersionUID = -331899757061728967L;
	@Id
	@Column(name = "Sequence_Name")
	private String sequenceName;

	@Column(name = "Sequence_Value")
	private String sequenceValue;

	@Column(name = "Description")
	private String description;

	@Column(name = "Active_Flag")
	private Boolean activeFlag;

	public String getSequenceName() {
		return sequenceName;
	}

	public void setSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
	}

	public String getSequenceValue() {
		return sequenceValue;
	}

	public void setSequenceValue(String sequenceValue) {
		this.sequenceValue = sequenceValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

}
