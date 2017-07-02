package com.fat.bigfarm.bean;


import java.io.Serializable;

public class ProductInfo extends BaseInfo implements Serializable
{

	private String id;
	private String imageUrl;//商品图片
	private String desc;
	private String price;
	private String cartid;

	private String sid;//商家id
	private String shopname;//商家名字

	private String unit;//单位

	private String aid;//活动id  不为0用活动价，为0还用price
	private String action_price;


	private boolean isTop;

	public boolean isTop() {
		return isTop;
	}

	public void setTop(boolean top) {
		isTop = top;
	}


	public String getSrcname() {
		return srcname;
	}

	public void setSrcname(String srcname) {
		this.srcname = srcname;
	}

	private String srcname;
	private int count;
	private int position;// 绝对位置，只在ListView构造的购物车中，在删除时有效

	public ProductInfo()
	{
		super();
	}

	public ProductInfo(String id, String name, String imageUrl
			, String desc, String aid,String action_price
			, String price, int count,String cartid
			,String sid,String shopname,String unit)
	{

		this.id = id;
		this.srcname = name;
		this.imageUrl = imageUrl;
		this.desc = desc;
		this.price = price;
		this.count = count;
		this.cartid = cartid;
		this.sid = sid;
		this.shopname = shopname;
		this.unit = unit;
		this.aid = aid;
		this.action_price = action_price;

	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getAction_price() {
		return action_price;
	}

	public void setAction_price(String action_price) {
		this.action_price = action_price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}


	public String getImageUrl()
	{
		return imageUrl;
	}

	public void setImageUrl(String imageUrl)
	{
		this.imageUrl = imageUrl;
	}

	public String getDesc()
	{
		return desc;
	}

	public void setDesc(String desc)
	{
		this.desc = desc;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public String getPrice()
	{
		return price;
	}

	public void setPrice(String price)
	{
		this.price = price;
	}

	public String getCartid()
	{
		return cartid;
	}

	public void setCartid(String cartid)
	{
		this.cartid = cartid;
	}

	public int getPosition()
	{
		return position;
	}

	public void setPosition(int position)
	{
		this.position = position;
	}

}
