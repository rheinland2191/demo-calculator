package com.interview.calculator.vo;

import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "total")
@XmlAccessorType(XmlAccessType.FIELD)
public class TotalVO extends ResourceSupport implements Serializable
{

    public TotalVO(Integer id, Integer qty, Integer total, List<CalcResultVO> detail) {
        super();
        this.total = total;
        this.detail = detail;
    }

    public TotalVO() {
    }


    private Integer total;
    private List<CalcResultVO> detail;

    public Integer getTotal() { return total; }

    public void setTotal(Integer total) {
        this.total = total;
    }
    public void setDetail(List<CalcResultVO> detail){ this.detail = detail; }
    public List<CalcResultVO> getDetail(){ return detail;}

}