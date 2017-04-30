package ahu.edu.cn.Util;

import java.util.ArrayList;
import java.util.List;



/**
 * 分页类
 * @author 杨喆
 *
 */
public class subPage<T> {
	/**
	 * 一页显示的数据条数
	 */
	private int pageSize;
	/**
	 * 当前为第几页
	 */
	private int currentPage;
	/**
	 * 总共多少条数据
	 */
	private int totalRecord;
	/**
	 * 总共多少页面
	 */
	private int totalPage;
	/**
	 * 存储数据
	 */
	private List<T> dataList;
	
	public subPage(){}
	public subPage(int pageNum, int pageSize, List<T> sourceList,int totalRecord){
		if(sourceList == null || sourceList.isEmpty()){
			dataList=new ArrayList<T>();
			this.totalRecord=0;
			this.pageSize = pageSize;
			this.totalPage=1;
			this.currentPage=1;
			return;
		}
		
		// 总记录条数
		this.totalRecord = totalRecord;
		
		// 每页显示多少条记录
		this.pageSize = pageSize;
		
		//获取总页数
		this.totalPage = this.totalRecord / this.pageSize;
		if(this.totalRecord % this.pageSize !=0){
			this.totalPage = this.totalPage + 1;
		}
		
		// 当前第几页数据
		this.currentPage = pageNum;
				
		this.dataList = sourceList;
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getDataList() {
		return dataList;
	}
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	
}
