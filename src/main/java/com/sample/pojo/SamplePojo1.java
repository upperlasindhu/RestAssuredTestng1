package com.sample.pojo;

public class SamplePojo1 
{
	
	public SamplePojo1()//default construcctor
	{
		
	}
	
	public SamplePojo1(String key1,String key2)//parametarized constructor
	{
		this.key1=key1;
		this.key2=key2;
	}
	
	public String getKey1()
	{
		return key1;
	}
	
	public void setKey1(String key1)
	{
		this.key1=key1;
		
	}
	
	
	public String getKey2() 
	{
		return key2;
	}
	
	
	 public void setKey2(String key2)
	 {
		 this.key2=key2;
	 }

	
	 private String key1;
	 private String key2;
	 
}
