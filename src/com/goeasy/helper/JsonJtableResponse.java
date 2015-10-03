package com.goeasy.helper;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.validation.ObjectError;

/**
 * This object is used for the response object for any controller methods used by the jTable library. It helps
 * to abstract away the details of the json response that jtable expects and will make the controllers cleaner
 * and jtable easier to use.
 *
 * This can return return results such as- error with an error message and ok with or without a list of results to
 * display in the table.
 *
 * Example usage:
 * return new JsonJtableResponse().error("Error message");
 *
 *
 */
public class JsonJtableResponse implements Serializable {

	private static final long serialVersionUID = 8046209322844760834L;

	/**
	 * The map to hold the jtable response, this will be sent as json by @ResponseBody.
	 * Example: {"Result":"ERROR","Message":"Some error message"}
	 */
	private Map<String, Object> jsonResponseMap;

	private static final String KEY_RESULT = "Result";
	private static final String RESULT_OK = "OK";
	private static final String RESULT_ERROR = "ERROR";
	private static final String RESULT_ERROR_SILENT = "ERROR_SILENT";

	private static final String KEY_RECORD = "Record"; //if result ok and insert
	private static final String KEY_RECORDS = "Records"; //if result ok and list
	private static final String KEY_MESSAGE = "Message"; //if result error

	public JsonJtableResponse() {
		jsonResponseMap = new HashMap<String, Object>();
	}

	/**
	 * This can be used by any jtable action to indicate the operation failed and provide the error message. This
	 * error message will be displayed in a popup and the table will show the default 'no data found' row.
	 *
	 * @param errorMessage the cause of the error
	 * @return the json respose object with result error and an error message
	 */
	public JsonJtableResponse error(String errorMessage) {
		jsonResponseMap.put(KEY_RESULT, RESULT_ERROR);
		jsonResponseMap.put(KEY_MESSAGE, errorMessage);
		return this;
	}

	/**
	 * This can be used by any jtable action to indicate the operation failed and provide the error message in the
	 * row of the table. The popup message is supressed with this method.
	 *
	 * @param errorMessage the cause of the error
	 * @return the json respose object with result error and an error message
	 */
	public JsonJtableResponse errorNoPopup(String errorMessage) {
		jsonResponseMap.put(KEY_RESULT, RESULT_ERROR_SILENT);
		jsonResponseMap.put(KEY_MESSAGE, errorMessage);
		return this;
	}

	/**
	 * This can be used by any jtable action to display a spring binding result error.
	 *
	 * @param errorMessage the cause of the error
	 * @return the json respose object with result error and an error message
	 */
	public JsonJtableResponse error(List<ObjectError> errorList) {
		jsonResponseMap.put(KEY_RESULT, RESULT_ERROR);
		StringBuilder sb = new StringBuilder();
		for (ObjectError err : errorList) {
			sb.append(err.getDefaultMessage()).append("");
		}
		jsonResponseMap.put(KEY_MESSAGE, sb.toString());
		return this;
	}

	/**
	 * This can be used by createAction to indicate the operation was successful, the new
	 * object must be passed in to this method.
	 *
	 * @param result the newly inserted object to add to the table
	 * @return the json respose object with result of 'ok'
	 */
	public JsonJtableResponse ok(Object result) {
		jsonResponseMap.put(KEY_RESULT, RESULT_OK);
		jsonResponseMap.put(KEY_RECORD, result);
		return this;
	}

	/**
	 * This can be used by listAction to indicate the operation was successful, and the list of object to
	 * be displayed by the table are passed in.
	 *
	 * @param resultList the list to diaplay in the jtable
	 * @return the json respose object with result of 'ok'
	 */
	public JsonJtableResponse ok(List<? extends Object> resultList) {
		jsonResponseMap.put(KEY_RESULT, RESULT_OK);
		jsonResponseMap.put(KEY_RECORDS, resultList);
		return this;
	}

	/**
	 * This can be used by the deleteAction and updateAction to indicate the operation
	 * was successful.
	 *
	 * @return the json respose object with result of 'ok'
	 */
	public JsonJtableResponse ok() {
		jsonResponseMap.put(KEY_RESULT, RESULT_OK);
		return this;
	}

	//@JsonValue
	public Map<String, Object> getJsonResponseMap() {
		return jsonResponseMap;
	}

}
