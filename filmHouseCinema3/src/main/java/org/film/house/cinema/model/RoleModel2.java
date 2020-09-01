package org.film.house.cinema.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import org.film.house.cinema.bean.RoleBean2;
import org.film.house.cinema.exception.ApplicationException;
import org.film.house.cinema.exception.DatabaseException2;
import org.film.house.cinema.exception.DuplicateRecordException2;
import org.film.house.cinema.util.JDBCDataSource;




public class RoleModel2 {

	private static Logger log = Logger.getLogger(RoleModel2.class);
	
	
	public Integer nextPK() throws DatabaseException2 {
		 log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM T_ROLE");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			 log.error("Database Exception..", e);
			throw new DatabaseException2("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		 log.debug("Model nextPK End");
		return pk + 1;
	}

	public long add(RoleBean2 bean) throws ApplicationException, DuplicateRecordException2 {
		 log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;
		RoleBean2 duplicataRole = findByName(bean.getName());

		// Check if create Role already exist
		if (duplicataRole != null) {
			throw new DuplicateRecordException2("Role already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO T_ROLE VALUES(?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDatetime());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			 log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		 log.debug("Model add End");
		return pk;
	}

	public RoleBean2 findByName(String name) throws ApplicationException {
		// log.debug("Model findBy EmailId Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM T_ROLE WHERE NAME=?");
		RoleBean2 bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new RoleBean2();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDatetime(rs.getTimestamp(6));
				bean.setModifiedDatetime(rs.getTimestamp(7));
			}
			rs.close();
		} catch (Exception e) {
			// log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by emailId");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Model findBy EmailId End");
		return bean;
	}

	
	
	   public void delete(RoleBean2 bean) throws ApplicationException {
	        //log.debug("Model delete Started");
	        Connection conn = null;
	        try {
	            conn = JDBCDataSource.getConnection();
	            conn.setAutoCommit(false); // Begin transaction
	            PreparedStatement pstmt = conn
	                    .prepareStatement("DELETE FROM T_ROLE WHERE ID=?");
	            pstmt.setLong(1, bean.getId());
	            pstmt.executeUpdate();
	            conn.commit(); // End transaction
	            pstmt.close();
	        } catch (Exception e) {
	          //  log.error("Database Exception..", e);
	            try {
	                conn.rollback();
	            } catch (Exception ex) {
	                throw new ApplicationException(
	                        "Exception : Delete rollback exception "
	                                + ex.getMessage());
	            }
	            throw new ApplicationException(
	                    "Exception : Exception in delete Role");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	       // log.debug("Model delete Started");
	    }
	   
	   
	   public RoleBean2 findByPK(long pk) throws ApplicationException {
	        log.debug("Model findByPK Started");
	        StringBuffer sql = new StringBuffer("SELECT * FROM T_ROLE WHERE ID=?");
	        RoleBean2 bean = null;
	        Connection conn = null;
	        try {
	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	            pstmt.setLong(1, pk);
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                bean = new RoleBean2();
	                bean.setId(rs.getLong(1));
	                bean.setName(rs.getString(2));
	                bean.setDescription(rs.getString(3));
	                bean.setCreatedBy(rs.getString(4));
	                bean.setModifiedBy(rs.getString(5));
	                bean.setCreatedDatetime(rs.getTimestamp(6));
	                bean.setModifiedDatetime(rs.getTimestamp(7));
	            }
	            rs.close();
	        } catch (Exception e) {
	           log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in getting User by pk");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model findByPK End");
	        return bean;
	    }
	   
	

}
