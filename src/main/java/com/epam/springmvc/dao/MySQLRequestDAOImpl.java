package com.epam.springmvc.dao;

import com.epam.springmvc.model.Request;
import com.mysql.jdbc.Connection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Profile("mysql")
@Configuration
public class MySQLRequestDAOImpl implements MySQLRequestDao {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void insert(Request request){

		String sql = "INSERT INTO REQUESTS " +
				"(ID, REQUESTOR, DESCRIPTION, EMAIL, ASSIGNEE, STATUS) VALUES (?, ?, ?, ?, ?, ?)";
		Connection conn = null;

		try {
			conn = (Connection) dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, request.getId());
			ps.setString(2, request.getRequestor());
			ps.setString(3, request.getDescription());
			ps.setString(4, request.getEmail());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}

	public Request findByRequestId(int custId){

		String sql = "SELECT * FROM REQUESTS WHERE ID = ?";

		Connection conn = null;

		try {
			conn = (Connection) dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, custId);
			Request request = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				request = new Request(
						rs.getLong("ID"),
						rs.getString("REQUESTOR"),
						rs.getString("DESCRIPTION"),
						rs.getString("EMAIL"),
						rs.getString("ASSIGNEE"),
						rs.getString("STATUS")
				);
			}
			rs.close();
			ps.close();
			return request;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}

}