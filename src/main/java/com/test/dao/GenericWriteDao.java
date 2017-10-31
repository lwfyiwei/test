package com.test.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.executor.BatchResult;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;


@Component
public class GenericWriteDao {
	
	@Resource
	private SqlSessionTemplate writeSqlSessionTemplate;
	
	public long generateSequence(String sqlNameWithNameSpace) {
		return writeSqlSessionTemplate.<Long> selectOne(sqlNameWithNameSpace);
	}

	
	
	public <T> int insertAndReturnAffectedCount(String sqlNameWithNameSpace, T obj) {
		return writeSqlSessionTemplate.insert(sqlNameWithNameSpace, obj);
	}

	
	
	public <T> int insertAndSetupId(String sqlNameWithNameSpace, T obj) {
		return writeSqlSessionTemplate.insert(sqlNameWithNameSpace, obj);
	}

	
	public int update(String sqlNameWithNameSpace, Map<String, Object> param) {
		return writeSqlSessionTemplate.update(sqlNameWithNameSpace, param);
	}

	
	public int delete(String sqlNameWithNameSpace,Object param) {
		return writeSqlSessionTemplate.delete(sqlNameWithNameSpace, param);
	}
	
	public int updateByObj(String sqlNameWithNameSpace, Object param) {
		return writeSqlSessionTemplate.update(sqlNameWithNameSpace, param);
	}

	
	public <T> T queryOne(String sqlNameWithNameSpace, Map<String, Object> map) {
		return writeSqlSessionTemplate.<T> selectOne(sqlNameWithNameSpace, map);
	}

	
	public Object queryObject(String sqlNameWithNameSpace, Map<String, Object> map) {
		return writeSqlSessionTemplate.selectOne(sqlNameWithNameSpace, map);
	}

	public <T> T queryOneByObject(String sqlNameWithNameSpace, String mapKey, Object mapValue) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(mapKey, mapValue);
		return writeSqlSessionTemplate.selectOne(sqlNameWithNameSpace, paramMap);
	}

	
	public <T> T queryUniqueById(String statement, long id) {
		return writeSqlSessionTemplate.selectOne(statement, id);
	}

	
	public <T> T queryUniqueById(String statement, String idStr) {
		return writeSqlSessionTemplate.selectOne(statement, idStr);
	}

	
	public <T> T queryUnique(String sqlNameWithNameSpace, Map<String, Object> map) {
		return writeSqlSessionTemplate.<T> selectOne(sqlNameWithNameSpace, map);
	}

	
	
	public int queryCount(String sqlNameWithNameSpace, Map<String, Object> map) {
		return writeSqlSessionTemplate.<Integer> selectOne(sqlNameWithNameSpace, map);
	}

	
	public int queryInt(String sqlNameWithNameSpace, Map<String, Object> map) {
		return writeSqlSessionTemplate.<Integer> selectOne(sqlNameWithNameSpace, map);
	}

	
	
	public <T> List<T> queryList(String sqlNameWithNameSpace, Map<String, Object> map) {
		return writeSqlSessionTemplate.selectList(sqlNameWithNameSpace, map);
	}
	
	public <K, V> Map<K, V> selectOneToMap(String sqlNameWithNameSpace, Map<K, V> param) {
		return writeSqlSessionTemplate.selectOne(sqlNameWithNameSpace, param);
	}

	
	public <T> DataPage<T> queryPage(String countSqlNameWithNameSpace, String rsSqlNameWithNameSpace,
			Map<String, Object> paramMap, DataPage<T> page) {
		if (page.isNeedTotalCount()) {
			int count = this.queryCount(countSqlNameWithNameSpace, paramMap);
			page.setTotalCount(count);
		}

		if (page.isNeedData()) {
			if (page.isNeedTotalCount()) {
				if (page.getTotalCount() > 0) {
					List<T> resultList = this.queryList(rsSqlNameWithNameSpace, paramMap, page);
					page.setDataList(resultList);
				} else {
					page.setDataList(new ArrayList<T>());
				}
			}
			if (!page.isNeedTotalCount()) {
				List<T> resultList = this.queryList(rsSqlNameWithNameSpace, paramMap, page);
				page.setDataList(resultList);
			}
		}
		return page;
	}

	public List<BatchResult> flushStatements() {
		return writeSqlSessionTemplate.flushStatements();
	}

	public <T> List<T> queryList(String sqlNameWithNameSpace, Map<String, Object> map, DataPage<T> page) {
		map.put("startIndex", page.getFirst());
		map.put("endIndex", page.getEndIndex());
		map.put("pageSize", page.getPageSize());
		return queryList(sqlNameWithNameSpace, map);
	}
	/**下面添加在查询时使用object作为入参  */
    
    public <T> T queryUnique(String sqlNameWithNameSpace, Object param) {
        return writeSqlSessionTemplate.<T> selectOne(sqlNameWithNameSpace, param);
    }

    
    public <T> T queryOne(String sqlNameWithNameSpace, Object param) {
        return writeSqlSessionTemplate.<T> selectOne(sqlNameWithNameSpace, param);
    }

    public Object queryObject(String sqlNameWithNameSpace, Object param) {
        return writeSqlSessionTemplate.selectOne(sqlNameWithNameSpace, param);
    }

    
    public int queryCount(String sqlNameWithNameSpace, Object param) {
        return writeSqlSessionTemplate.<Integer> selectOne(sqlNameWithNameSpace, param);
    }

    
    public int queryInt(String sqlNameWithNameSpace, Object param) {
        return writeSqlSessionTemplate.<Integer> selectOne(sqlNameWithNameSpace, param);
    }

    
    public <T> List<T> queryList(String sqlNameWithNameSpace, Object param,
            DataPage<T> page) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startIndex", page.getFirst());
        map.put("endIndex", page.getEndIndex());
        map.put("pageSize", page.getPageSize());
        map.put("param", param);
        return queryList(sqlNameWithNameSpace, map);
    }

    
    public <T> DataPage<T> queryPage(String countSqlNameWithNameSpace,
            String rsSqlNameWithNameSpace, Object param, DataPage<T> page) {
        if (page.isNeedTotalCount()) {
            int count = this.queryCount(countSqlNameWithNameSpace, param);
            page.setTotalCount(count);
        }

        if (page.isNeedData()) {
            if (page.isNeedTotalCount()) {
                if (page.getTotalCount() > 0) {
                    List<T> resultList = this.queryList(rsSqlNameWithNameSpace, param, page);
                    page.setDataList(resultList);
                } else {
                    page.setDataList(new ArrayList<T>());
                }
            }
            if (!page.isNeedTotalCount()) {
                List<T> resultList = this.queryList(rsSqlNameWithNameSpace, param, page);
                page.setDataList(resultList);
            }
        }
        return page;
    }

    public <T> List<T> queryList(String sqlNameWithNameSpace, Object param) {
        return writeSqlSessionTemplate.selectList(sqlNameWithNameSpace, param);
    }
    
    public <K, V> Map<K, V> selectOneToMap(String sqlNameWithNameSpace,
            Object param) throws Exception {
        return writeSqlSessionTemplate.selectOne(sqlNameWithNameSpace, param);
    }
	

}
