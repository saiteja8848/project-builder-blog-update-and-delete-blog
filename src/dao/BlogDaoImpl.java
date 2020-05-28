package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Blog;
import utility.ConnectionManager;

public class BlogDaoImpl implements BlogDaoInterface {

	public void insertBlog(Blog blog) throws SQLException {
		if (ConnectionManager.getConnection() != null) {
			Statement stmt = ConnectionManager.getConnection().createStatement();
			String sql = "INSERT INTO BLOG VALUES(" + blog.getBlogId() + "," + blog.getBlogTitle() + ","
					+ blog.getBlogDescription() + "," + "DATE" + " " + "'" + blog.getPostedOn() + "'" + ")";
			stmt.executeUpdate(sql);
		}

	}

	public Blog selectBlog(int blogid) throws SQLException {
		Blog blog = null;
		if (ConnectionManager.getConnection() != null) {
			Statement stmt = ConnectionManager.getConnection().createStatement();
			String sql = "SELECT * FROM BLOG WHERE BLOGID =" + blogid;
			ResultSet rs = stmt.executeQuery(sql);
			blog = new Blog();
			while(rs.next()) {
			blog.setBlogId(rs.getInt(1));
			blog.setBlogTitle(rs.getString(2));
			blog.setBlogDescription(rs.getString(3));
			blog.setPostedOn(LocalDate.now());
			}
		}
		return blog;
	}

	public List<Blog> selectAllBlogs() throws SQLException {
		List<Blog> blogList = null;
		if (ConnectionManager.getConnection() != null) {
			System.out.println("established");
			blogList = new ArrayList<>();
			Statement stmt = ConnectionManager.getConnection().createStatement();
			String sql = "SELECT * FROM BLOG";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Blog blog = new Blog();
				blog.setBlogId(rs.getInt(1));
				blog.setBlogTitle(rs.getString(2));
				blog.setBlogDescription(rs.getString(3));
				blog.setPostedOn(LocalDate.now());
				blogList.add(blog);
			}
			System.out.println(blogList);
			return blogList;
		} else {
			System.out.println("no data presnet");

			return blogList;
		}

	}

	public boolean deleteBlog(int id) throws SQLException {
		int i = -1;
		if (ConnectionManager.getConnection() != null) {
			Statement stmt = ConnectionManager.getConnection().createStatement();
			String sql = "DELETE FROM BLOG WHERE BLOGID =" + id;
			i = stmt.executeUpdate(sql);

		}
		if (i > 0)
			return true;
		else
			return false;
	}

	public boolean updateBlog(Blog blog) throws SQLException, Exception {
		System.out.println("UPDATINGS");
		int i = -1;
		if (ConnectionManager.getConnection() != null) {
			Statement stmt = ConnectionManager.getConnection().createStatement();
			String sql = "UPDATE BLOG SET BLOGTITLE = "+"'"+blog.getBlogTitle()+"'"+"WHERE BLOGID ="+blog.getBlogId();
			i = stmt.executeUpdate(sql);
		}
		if (i > 0)
			return true;
		else
			return false;

	}

}