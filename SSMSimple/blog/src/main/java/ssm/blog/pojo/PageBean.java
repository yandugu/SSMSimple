package ssm.blog.pojo;

import java.util.List;

/**
 * Created by xp on 2017/4/14.
 */
public class PageBean<T> {

    private int currPage;   //��ǰҳ��
    private int pageSize;   //ÿҳ��ʾ�ĸ���
    private long total;      //�ܼ�¼��
    private int start;
    private int end;
    private List<T> result; //��ҳ��ѯ�Ľ��

    PageBean(){

    }

    public PageBean(int currPage, int pageSize) {
        this.currPage = currPage;
        this.pageSize = pageSize;
        this.start = (currPage-1)*pageSize;
        this.end = pageSize;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "currPage=" + currPage +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", start=" + start +
                ", end=" + end +
                ", result=" + result +
                '}';
    }
}
