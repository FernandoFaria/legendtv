package com.googlecode.legendtv;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.axis2.transport.http.HttpTransportProperties.Authenticator;

import com.googlecode.legendtv.data.Lineup;
import com.googlecode.legendtv.data.Schedule;

public class DataDirectRequestor
{
	private static final String DATADIRECT_NS_URI		= "urn:TMSWebServices";
	private static final String DATADIRECT_SOAP_ACTION	= "urn:TMSWebServices:xtvdWebService#download";
	private static final String DATADIRECT_SOAP_URL		= "http://datadirect.webservices.zap2it.com/tvlistings/xtvdService";
	private static final String DATADIRECT_AUTH_REALM	= "TMSWebServiceRealm";
	private static final String DATADIRECT_TIME_FORMAT	= "yyyy-MM-dd'T'HH:mm:ss'Z'";
	private static final int	DATADIRECT_MAX_DAYS		= 13;
	
	private	int 			numDays;
	
	public DataDirectRequestor()
	{
		this(DATADIRECT_MAX_DAYS);
	}
	
	public DataDirectRequestor(int numDays)
	{
		this.numDays	= numDays;
	}
	
	private Date getStartDate()
	{
		Calendar	startCal	= Calendar.getInstance();
		
		startCal.set(Calendar.HOUR_OF_DAY,	0);
		startCal.set(Calendar.MINUTE, 		0);
		startCal.set(Calendar.SECOND, 		0);
		startCal.set(Calendar.MILLISECOND,	0);
		
		return (startCal.getTime());
	}
	
	private Date getEndDate(int numDays)
	{
		Calendar	endCal	= Calendar.getInstance();
		
		// Start off using the start date
		endCal.setTime(getStartDate());
		
		// Add the number of days required
		endCal.add(Calendar.DATE, numDays);
		
		return (endCal.getTime());
	}
	
	private OMElement assembleMessage(Lineup lineup)
	{
		OMFactory		omFac		= OMAbstractFactory.getOMFactory();
		OMElement		retVal,
						startElem,
						endElem;
		Date			startDate	= getStartDate(),
						endDate		= getEndDate(numDays);
		
		retVal		= omFac.createOMElement("download", omFac.createOMNamespace(DATADIRECT_NS_URI, "tms"));
		
		// Start Time
		startElem	= omFac.createOMElement("startTime", null);
		
		retVal.addChild(startElem);
		
		startElem.addAttribute("xsi:type", "tms:dateTime", null);
		startElem.setText(new SimpleDateFormat(DATADIRECT_TIME_FORMAT).format(startDate));
		
		// End Time
		endElem		= omFac.createOMElement("endTime", null);
		
		retVal.addChild(endElem);
		
		endElem.addAttribute("xsi:type", "tms:dateTime", null);
		endElem.setText(new SimpleDateFormat(DATADIRECT_TIME_FORMAT).format(endDate));
		
		return retVal;
	}
	
	public List<Lineup> getLineups()
	{
		throw new UnsupportedOperationException("Implement me!");
	}
	
	public List<Schedule> getGuideData(Lineup lineup)
	{
		List<Schedule>	retVal	= new LinkedList<Schedule>();
		
		try
		{
			OMElement				request	= assembleMessage(lineup),
									resp;
			Authenticator			auth	= new HttpTransportProperties.Authenticator();
			Options					opts	= new Options();
			ServiceClient			client	= new ServiceClient();
			
			auth.setUsername("GuyPaddock");
			auth.setPassword("GuyIsCool");
			
			opts.setAction(DATADIRECT_SOAP_ACTION);
			opts.setTo(new EndpointReference(DATADIRECT_SOAP_URL));
			opts.setProperty(HTTPConstants.AUTHENTICATE, auth);
			opts.setProperty(HTTPConstants.COMPRESSION_GZIP, true);
			opts.setProperty(HTTPConstants.MC_ACCEPT_GZIP, true);
			
			client.setOptions(opts);
			
			System.out.println("Request:");
			System.out.println(request);
			
			resp = client.sendReceive(request);
			
			System.out.println("Response:");
			resp.serialize(XMLOutputFactory.newInstance().createXMLStreamWriter(System.out));
		}
		
		catch (Throwable except)
		{
			except.printStackTrace();
		}
		
		return retVal;
	}

	public static void main(String[] args)
	{
		DataDirectRequestor	requestor	= new DataDirectRequestor();
	
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
		System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
		System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire.header", "debug");
		System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient", "debug");
		
		requestor.getGuideData(null);
	}
}
