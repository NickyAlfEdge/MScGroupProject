package com.team.project.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GroupPreferenceExample {

    protected String orderByClause;
    protected boolean distinct;
    protected List<Criteria> oredCriteria;

    public GroupPreferenceExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNull() {
            addCriterion("student_id is null");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNotNull() {
            addCriterion("student_id is not null");
            return (Criteria) this;
        }

        public Criteria andStudentIdEqualTo(Integer value) {
            addCriterion("student_id =", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotEqualTo(Integer value) {
            addCriterion("student_id <>", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThan(Integer value) {
            addCriterion("student_id >", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("student_id >=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThan(Integer value) {
            addCriterion("student_id <", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThanOrEqualTo(Integer value) {
            addCriterion("student_id <=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdIn(List<Integer> values) {
            addCriterion("student_id in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotIn(List<Integer> values) {
            addCriterion("student_id not in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdBetween(Integer value1, Integer value2) {
            addCriterion("student_id between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("student_id not between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andLikePersonIdIsNull() {
            addCriterion("like_person_id is null");
            return (Criteria) this;
        }

        public Criteria andLikePersonIdIsNotNull() {
            addCriterion("like_person_id is not null");
            return (Criteria) this;
        }

        public Criteria andLikePersonIdEqualTo(Integer value) {
            addCriterion("like_person_id =", value, "likePersonId");
            return (Criteria) this;
        }

        public Criteria andLikePersonIdNotEqualTo(Integer value) {
            addCriterion("like_person_id <>", value, "likePersonId");
            return (Criteria) this;
        }

        public Criteria andLikePersonIdGreaterThan(Integer value) {
            addCriterion("like_person_id >", value, "likePersonId");
            return (Criteria) this;
        }

        public Criteria andLikePersonIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("like_person_id >=", value, "likePersonId");
            return (Criteria) this;
        }

        public Criteria andLikePersonIdLessThan(Integer value) {
            addCriterion("like_person_id <", value, "likePersonId");
            return (Criteria) this;
        }

        public Criteria andLikePersonIdLessThanOrEqualTo(Integer value) {
            addCriterion("like_person_id <=", value, "likePersonId");
            return (Criteria) this;
        }

        public Criteria andLikePersonIdIn(List<Integer> values) {
            addCriterion("like_person_id in", values, "likePersonId");
            return (Criteria) this;
        }

        public Criteria andLikePersonIdNotIn(List<Integer> values) {
            addCriterion("like_person_id not in", values, "likePersonId");
            return (Criteria) this;
        }

        public Criteria andLikePersonIdBetween(Integer value1, Integer value2) {
            addCriterion("like_person_id between", value1, value2, "likePersonId");
            return (Criteria) this;
        }

        public Criteria andLikePersonIdNotBetween(Integer value1, Integer value2) {
            addCriterion("like_person_id not between", value1, value2, "likePersonId");
            return (Criteria) this;
        }

        public Criteria andDislikePersonIdIsNull() {
            addCriterion("dislike_person_id is null");
            return (Criteria) this;
        }

        public Criteria andDislikePersonIdIsNotNull() {
            addCriterion("dislike_person_id is not null");
            return (Criteria) this;
        }

        public Criteria andDislikePersonIdEqualTo(Integer value) {
            addCriterion("dislike_person_id =", value, "dislikePersonId");
            return (Criteria) this;
        }

        public Criteria andDislikePersonIdNotEqualTo(Integer value) {
            addCriterion("dislike_person_id <>", value, "dislikePersonId");
            return (Criteria) this;
        }

        public Criteria andDislikePersonIdGreaterThan(Integer value) {
            addCriterion("dislike_person_id >", value, "dislikePersonId");
            return (Criteria) this;
        }

        public Criteria andDislikePersonIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("dislike_person_id >=", value, "dislikePersonId");
            return (Criteria) this;
        }

        public Criteria andDislikePersonIdLessThan(Integer value) {
            addCriterion("dislike_person_id <", value, "dislikePersonId");
            return (Criteria) this;
        }

        public Criteria andDislikePersonIdLessThanOrEqualTo(Integer value) {
            addCriterion("dislike_person_id <=", value, "dislikePersonId");
            return (Criteria) this;
        }

        public Criteria andDislikePersonIdIn(List<Integer> values) {
            addCriterion("dislike_person_id in", values, "dislikePersonId");
            return (Criteria) this;
        }

        public Criteria andDislikePersonIdNotIn(List<Integer> values) {
            addCriterion("dislike_person_id not in", values, "dislikePersonId");
            return (Criteria) this;
        }

        public Criteria andDislikePersonIdBetween(Integer value1, Integer value2) {
            addCriterion("dislike_person_id between", value1, value2, "dislikePersonId");
            return (Criteria) this;
        }

        public Criteria andDislikePersonIdNotBetween(Integer value1, Integer value2) {
            addCriterion("dislike_person_id not between", value1, value2, "dislikePersonId");
            return (Criteria) this;
        }

        public Criteria andDislikePersonTwoIdIsNull() {
            addCriterion("dislike_person_two_id is null");
            return (Criteria) this;
        }

        public Criteria andDislikePersonTwoIdIsNotNull() {
            addCriterion("dislike_person_two_id is not null");
            return (Criteria) this;
        }

        public Criteria andDislikePersonTwoIdEqualTo(Integer value) {
            addCriterion("dislike_person_two_id =", value, "dislikePersonTwoId");
            return (Criteria) this;
        }

        public Criteria andDislikePersonTwoIdNotEqualTo(Integer value) {
            addCriterion("dislike_person_two_id <>", value, "dislikePersonTwoId");
            return (Criteria) this;
        }

        public Criteria andDislikePersonTwoIdGreaterThan(Integer value) {
            addCriterion("dislike_person_two_id >", value, "dislikePersonTwoId");
            return (Criteria) this;
        }

        public Criteria andDislikePersonTwoIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("dislike_person_two_id >=", value, "dislikePersonTwoId");
            return (Criteria) this;
        }

        public Criteria andDislikePersonTwoIdLessThan(Integer value) {
            addCriterion("dislike_person_two_id <", value, "dislikePersonTwoId");
            return (Criteria) this;
        }

        public Criteria andDislikePersonTwoIdLessThanOrEqualTo(Integer value) {
            addCriterion("dislike_person_two_id <=", value, "dislikePersonTwoId");
            return (Criteria) this;
        }

        public Criteria andDislikePersonTwoIdIn(List<Integer> values) {
            addCriterion("dislike_person_two_id in", values, "dislikePersonTwoId");
            return (Criteria) this;
        }

        public Criteria andDislikePersonTwoIdNotIn(List<Integer> values) {
            addCriterion("dislike_person_two_id not in", values, "dislikePersonTwoId");
            return (Criteria) this;
        }

        public Criteria andDislikePersonTwoIdBetween(Integer value1, Integer value2) {
            addCriterion("dislike_person_two_id between", value1, value2, "dislikePersonTwoId");
            return (Criteria) this;
        }

        public Criteria andDislikePersonTwoIdNotBetween(Integer value1, Integer value2) {
            addCriterion("dislike_person_two_id not between", value1, value2, "dislikePersonTwoId");
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