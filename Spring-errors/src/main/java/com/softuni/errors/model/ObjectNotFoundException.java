package com.softuni.errors.model;

public class ObjectNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 6123866955867792339L;
	private Long objectId;
	private String objectType;

	public ObjectNotFoundException(Long objectId, String objectType) {
		super("Object with ID " + objectId + " and type " + objectType + " not found!");
		this.objectId = objectId;
		this.objectType = objectType;
	}

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
}
