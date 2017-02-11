package com.aheroboy.prophets.framework;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=true)
public class AbstractEntity extends ID {
	private Date createdDate;
	private Date updatedDate;
	private String bizId;
	private long version;
	private String name;
	private boolean isLeaf;
	public AbstractEntity(){
		Date newDate = new Date();
		this.createdDate = newDate;
		this.updatedDate = newDate;
		this.version = 1;
	}

	public void inc(){
		this.version++;
	}

}
