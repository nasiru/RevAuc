dataSource { pooled = true }
hibernate {
	cache.use_second_level_cache = true
	cache.use_query_cache = false
	cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
	development {
		dataSource {
			dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
			driverClassName = "org.h2.Driver"
			url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
			username = "sa"
			password = ""
		}
	}
	test {
		dataSource {
			dbCreate = "update"
			driverClassName = "org.h2.Driver"
			url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
			username = "sa"
			password = ""
		}
	}
	production {
		dataSource {
			driverClassName = "com.mysql.jdbc.Driver"
			dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"

			username = "root"
			password = "Database_2013"

			dbCreate = "update"
			url = "jdbc:mysql://localhost/revauc?useUnicode=yes&characterEncoding=UTF-8"
			pooled = true
			properties {
				maxActive = -1
				minEvictableIdleTimeMillis=1800000
				timeBetweenEvictionRunsMillis=1800000
				numTestsPerEvictionRun=3
				testOnBorrow=true
				testWhileIdle=true
				testOnReturn=true
				validationQuery="SELECT 1"
			}
		}
	}
}
