package org.socraticgrid.hl7.services.eps.internal;

import java.util.StringTokenizer;

import org.socraticgrid.hl7.services.eps.internal.interfaces.TopicIFace;
import org.socraticgrid.hl7.services.eps.internal.interfaces.TopicLocatorIFace;

public class StaticTopicTree implements TopicLocatorIFace {

	private TopicIFace rootTopic;
	
	@Override
	public TopicIFace locateTopic(String path) {
		
		TopicIFace cur = rootTopic;
		//Handle Root case
		if (path.compareTo("/")!=0)
		{
			//Loop down each element
			StringTokenizer tok = new StringTokenizer(path,"/");
			while(tok.hasMoreTokens())
			{
				String name = tok.nextToken();
				cur = cur.getSubTopic(name);
				if (cur == null)
				{
					break;
				}
			}
		}
		return cur;
	}

	/**
	 * @return the rootTopic
	 */
	public TopicIFace getRootTopic() {
		return rootTopic;
	}

	/**
	 * @param rootTopic the rootTopic to set
	 */
	public void setRootTopic(TopicIFace rootTopic) {
		this.rootTopic = rootTopic;
	}

}
