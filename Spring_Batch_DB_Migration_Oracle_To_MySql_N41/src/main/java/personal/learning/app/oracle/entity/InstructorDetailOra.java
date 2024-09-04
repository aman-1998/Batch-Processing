package personal.learning.app.oracle.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "instructor_detail")
public class InstructorDetailOra {
	
	@Id
	private BigInteger id;
	
	@Column(name = "youtube_channel")
	private String youtubeChannel;
	
	@Column(name = "channel_description")
	private String channelDescription;
	
	@Column(name = "no_of_subscriber")
	private BigInteger noOfSubscriber;
	
	@Column(name = "instructor_id")
	private BigInteger instructorId;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getYoutubeChannel() {
		return youtubeChannel;
	}

	public void setYoutubeChannel(String youtubeChannel) {
		this.youtubeChannel = youtubeChannel;
	}

	public String getChannelDescription() {
		return channelDescription;
	}

	public void setChannelDescription(String channelDescription) {
		this.channelDescription = channelDescription;
	}

	public BigInteger getNoOfSubscriber() {
		return noOfSubscriber;
	}

	public void setNoOfSubscriber(BigInteger noOfSubscriber) {
		this.noOfSubscriber = noOfSubscriber;
	}

	public BigInteger getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(BigInteger instructorId) {
		this.instructorId = instructorId;
	}

	@Override
	public String toString() {
		return "InstructorDetailOra [id=" + id + ", youtubeChannel=" + youtubeChannel + ", channelDescription="
				+ channelDescription + ", noOfSubscriber=" + noOfSubscriber + ", instructorId=" + instructorId + "]";
	}
	
}
