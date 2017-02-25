package com.aheroboy.prophets.framework;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=true)
public class AbstractEntity extends ID {
	private String createdDate;
	private String updatedDate;
	private String bizId;
	private long version;
	private String name;
	private boolean isLeaf;
	public AbstractEntity(){
		String newDate = Utils.YYYY_MM_DD_HHMMSS.get().format(new Date());
		this.createdDate = newDate;
		this.updatedDate = newDate;
		this.version = 1;
	}

	public void inc(){
		this.version++;
	}

}
