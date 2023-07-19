package com.todoserivce.repository;

import com.todoserivce.domain.todo.ToDoItem;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ToDoDbRepository implements ToDoRepository {
    private final DataSource dataSource;
    @Override
    public ToDoItem save(ToDoItem toDoItem) throws SQLException {
        String sql = "insert into toDoItem(context,status) values(?,?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs  = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,toDoItem.getContext());
//            pstmt.setString(2,"f");
            pstmt.setBoolean(2,false);//추가
            pstmt.executeUpdate();
            rs= pstmt.getGeneratedKeys();
            if(rs.next()){
                toDoItem.setNo(rs.getLong(1));
                toDoItem.setStatus(false);
                System.out.println(toDoItem.getNo());
                System.out.println(toDoItem.getContext());
                System.out.println(toDoItem.getStatus());
                return toDoItem;
            }
            return null;
        } catch (Exception e){
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public ToDoItem findByNo(Long no) {
        String sql = "select * from toDoItem where no = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, no);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                ToDoItem toDoItem = new ToDoItem();
                toDoItem.setNo(rs.getLong("no"));
                toDoItem.setContext(rs.getString("context"));
                toDoItem.setStatus(rs.getBoolean("status"));
                return toDoItem;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<ToDoItem> findAll() {

        String sql = "select * from toDoItem";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<ToDoItem> toDoItems = new ArrayList<>();
            while(rs.next()) {
                ToDoItem toDoItem = new ToDoItem();
                toDoItem.setNo(rs.getLong("no"));
                toDoItem.setContext(rs.getString("context"));
                toDoItem.setStatus(rs.getBoolean("status"));
                toDoItems.add(toDoItem);
            }
            return toDoItems;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }

    }

    @Override
    public void contextUpdate(Long no, String context) {
        String sql = "update toDoItem set context=? where no=?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs  = null;

        try {
            ToDoItem toDoItem = findByNo(no);
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,context);
            pstmt.setLong(2, no);
            pstmt.executeUpdate();
            rs= pstmt.getGeneratedKeys();
            if(rs.next()){
                toDoItem.setContext(context);
            }
        } catch (Exception e){
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public void statusUpdate(Long no) {
        String sql = "update toDoItem set status=? where no=?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs  = null;

        try {
            ToDoItem toDoItem = findByNo(no);
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setBoolean(1,!findByNo(no).getStatus());
            pstmt.setLong(2, no);
            pstmt.executeUpdate();
            rs= pstmt.getGeneratedKeys();
            if(rs.next()){
                toDoItem.setStatus(toDoItem.getStatus());
            }
        } catch (Exception e){
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public void deleteItem(Long no) {
        String sql = "delete from toDoItem where no ="+no;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs  = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (Exception e){
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}
