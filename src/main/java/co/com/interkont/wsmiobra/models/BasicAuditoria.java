package co.com.interkont.wsmiobra.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@MappedSuperclass
@JsonIgnoreProperties(value= {"createdAt","updatedAt","createdBy","updateBy"})
public class BasicAuditoria {
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(name="created_at", nullable=false, updatable=false)
	private Date craetedAt;

	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	@Column(name="updated_at",nullable=false)
	private Date updatedAt;
	
	@Column(name="create_by")
	@CreatedBy
	private String createBy;
	
	@Column(name="update_by")
	@LastModifiedBy
	private String update_by;

	public Date getCraetedAt() {
		return craetedAt;
	}

	public void setCraetedAt(Date craetedAt) {
		this.craetedAt = craetedAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdate_by() {
		return update_by;
	}

	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}
	
}