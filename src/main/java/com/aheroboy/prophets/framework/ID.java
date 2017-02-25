package com.aheroboy.prophets.framework;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class ID {
	private String id;

	public ID() {
		this.id = UUID.randomUUID().toString();
	}
}
