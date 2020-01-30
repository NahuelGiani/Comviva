package ar.com.ejercicioTres.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table (name = "COMVIVA_FILE")
public class ComvivaFile implements Serializable{
	
	private static final long serialVersionUID = -7799949074295958397L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "FILENAME")
	private String filename;
	
	@Column(name = "FILEVALUE")
	private Double filevalue;
	
	@Column(name = "PROCESSDATE")
	private Date processdate;

	public ComvivaFile() {}
	
	public ComvivaFile(String filename, Double filevalue) {
		this.filename = filename;
		this.filevalue = filevalue;
		this.processdate = new Date();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filename == null) ? 0 : filename.hashCode());
		result = prime * result + ((filevalue == null) ? 0 : filevalue.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((processdate == null) ? 0 : processdate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComvivaFile other = (ComvivaFile) obj;
		if (filename == null) {
			if (other.filename != null)
				return false;
		} else if (!filename.equals(other.filename))
			return false;
		if (filevalue == null) {
			if (other.filevalue != null)
				return false;
		} else if (!filevalue.equals(other.filevalue))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (processdate == null) {
			if (other.processdate != null)
				return false;
		} else if (!processdate.equals(other.processdate))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Double getFilevalue() {
		return filevalue;
	}

	public void setFilevalue(Double filevalue) {
		this.filevalue = filevalue;
	}

	public Date getProcessdate() {
		return processdate;
	}

	public void setProcessdate(Date processdate) {
		this.processdate = processdate;
	}
	
}
