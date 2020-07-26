package com.team.project.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MarkCriterionExample {

    protected String orderByClause;
    protected boolean distinct;
    protected List<Criteria> oredCriteria;

    public MarkCriterionExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        return new Criteria();
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andCriterionIdIsNull() {
            addCriterion("criterion_id is null");
            return (Criteria) this;
        }

        public Criteria andCriterionIdIsNotNull() {
            addCriterion("criterion_id is not null");
            return (Criteria) this;
        }

        public Criteria andCriterionIdEqualTo(Integer value) {
            addCriterion("criterion_id =", value, "criterionId");
            return (Criteria) this;
        }

        public Criteria andCriterionIdNotEqualTo(Integer value) {
            addCriterion("criterion_id <>", value, "criterionId");
            return (Criteria) this;
        }

        public Criteria andCriterionIdGreaterThan(Integer value) {
            addCriterion("criterion_id >", value, "criterionId");
            return (Criteria) this;
        }

        public Criteria andCriterionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("criterion_id >=", value, "criterionId");
            return (Criteria) this;
        }

        public Criteria andCriterionIdLessThan(Integer value) {
            addCriterion("criterion_id <", value, "criterionId");
            return (Criteria) this;
        }

        public Criteria andCriterionIdLessThanOrEqualTo(Integer value) {
            addCriterion("criterion_id <=", value, "criterionId");
            return (Criteria) this;
        }

        public Criteria andCriterionIdIn(List<Integer> values) {
            addCriterion("criterion_id in", values, "criterionId");
            return (Criteria) this;
        }

        public Criteria andCriterionIdNotIn(List<Integer> values) {
            addCriterion("criterion_id not in", values, "criterionId");
            return (Criteria) this;
        }

        public Criteria andCriterionIdBetween(Integer value1, Integer value2) {
            addCriterion("criterion_id between", value1, value2, "criterionId");
            return (Criteria) this;
        }

        public Criteria andCriterionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("criterion_id not between", value1, value2, "criterionId");
            return (Criteria) this;
        }

        public Criteria andCriterionNameIsNull() {
            addCriterion("criterion_name is null");
            return (Criteria) this;
        }

        public Criteria andCriterionNameIsNotNull() {
            addCriterion("criterion_name is not null");
            return (Criteria) this;
        }

        public Criteria andCriterionNameEqualTo(String value) {
            addCriterion("criterion_name =", value, "criterionName");
            return (Criteria) this;
        }

        public Criteria andCriterionNameNotEqualTo(String value) {
            addCriterion("criterion_name <>", value, "criterionName");
            return (Criteria) this;
        }

        public Criteria andCriterionNameGreaterThan(String value) {
            addCriterion("criterion_name >", value, "criterionName");
            return (Criteria) this;
        }

        public Criteria andCriterionNameGreaterThanOrEqualTo(String value) {
            addCriterion("criterion_name >=", value, "criterionName");
            return (Criteria) this;
        }

        public Criteria andCriterionNameLessThan(String value) {
            addCriterion("criterion_name <", value, "criterionName");
            return (Criteria) this;
        }

        public Criteria andCriterionNameLessThanOrEqualTo(String value) {
            addCriterion("criterion_name <=", value, "criterionName");
            return (Criteria) this;
        }

        public Criteria andCriterionNameLike(String value) {
            addCriterion("criterion_name like", value, "criterionName");
            return (Criteria) this;
        }

        public Criteria andCriterionNameNotLike(String value) {
            addCriterion("criterion_name not like", value, "criterionName");
            return (Criteria) this;
        }

        public Criteria andCriterionNameIn(List<String> values) {
            addCriterion("criterion_name in", values, "criterionName");
            return (Criteria) this;
        }

        public Criteria andCriterionNameNotIn(List<String> values) {
            addCriterion("criterion_name not in", values, "criterionName");
            return (Criteria) this;
        }

        public Criteria andCriterionNameBetween(String value1, String value2) {
            addCriterion("criterion_name between", value1, value2, "criterionName");
            return (Criteria) this;
        }

        public Criteria andCriterionNameNotBetween(String value1, String value2) {
            addCriterion("criterion_name not between", value1, value2, "criterionName");
            return (Criteria) this;
        }

        public Criteria andTotalIsNull() {
            addCriterion("total is null");
            return (Criteria) this;
        }

        public Criteria andTotalIsNotNull() {
            addCriterion("total is not null");
            return (Criteria) this;
        }

        public Criteria andTotalEqualTo(Integer value) {
            addCriterion("total =", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotEqualTo(Integer value) {
            addCriterion("total <>", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThan(Integer value) {
            addCriterion("total >", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("total >=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThan(Integer value) {
            addCriterion("total <", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThanOrEqualTo(Integer value) {
            addCriterion("total <=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalIn(List<Integer> values) {
            addCriterion("total in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotIn(List<Integer> values) {
            addCriterion("total not in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalBetween(Integer value1, Integer value2) {
            addCriterion("total between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("total not between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
