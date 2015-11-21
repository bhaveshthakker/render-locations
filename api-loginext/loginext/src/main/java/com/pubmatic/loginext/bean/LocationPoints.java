package com.pubmatic.loginext.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Entity
@Table(name="location_points")
public class LocationPoints {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	
	
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	@Column(columnDefinition = "varchar(255) DEFAULT 'NA'")
	private String title;
	
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	@Column(insertable = true, updatable = true)
    public Double latitude;

	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
    @Column(insertable = true, updatable = true)
    public Double longitude;
    
    @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	@Column(columnDefinition = "tinyint DEFAULT 0")
	private Boolean is_deleted;
	
/*	@JsonIgnore
	@Temporal(TemporalType.DATE)
	@Column(columnDefinition = "TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false, updatable = true)
	private Date updatedAt;
*/	
	@PrePersist
	public void preUpdate() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Boolean getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(Boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	
	/*@PostPersist
	public void postUpdate() {
		setUpdatedAt(new Date());
	}*/
}
