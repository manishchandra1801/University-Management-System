package com.seterm.web.model;

import java.io.InputStream;
import java.io.OutputStream;

public class Filetoupload {
	public String getFileid() {
		return fileid;
	}

	public void setFileid(String fileid) {
		this.fileid = fileid;
	}

	private String fileid;
	private InputStream inputStream;
	private String filename;
	private OutputStream outputstream;
	private String contenttype;
	
	public String getContenttype() {
		return contenttype;
	}

	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}

	public OutputStream getOutputstream() {
		return outputstream;
	}

	public void setOutputstream(OutputStream outputstream) {
		this.outputstream = outputstream;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
}
