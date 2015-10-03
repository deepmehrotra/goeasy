package com.goeasy.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="OrderTimeline")
public class OrderTimeline {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private int timelineId;
	@Column
	private Date eventDate;
	@Column
	private String event;
	@Column
	private String eventRemark;
	public int getTimelineId() {
		return timelineId;
	}
	public void setTimelineId(int timelineId) {
		this.timelineId = timelineId;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getEventRemark() {
		return eventRemark;
	}
	public void setEventRemark(String eventRemark) {
		this.eventRemark = eventRemark;
	}
	@Override
	public String toString() {
		return "OrderTimeline [timelineId=" + timelineId + ", eventDate="
				+ eventDate + ", event=" + event + ", eventRemark="
				+ eventRemark + "]";
	}

}
