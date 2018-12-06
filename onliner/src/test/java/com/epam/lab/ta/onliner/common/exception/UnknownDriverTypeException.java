package com.epam.lab.ta.onliner.common.exception;

public class UnknownDriverTypeException extends RuntimeException {

	private static final long serialVersionUID = -4330888418140567546L;

	public UnknownDriverTypeException(String msg) {
		super(msg);
	}
}