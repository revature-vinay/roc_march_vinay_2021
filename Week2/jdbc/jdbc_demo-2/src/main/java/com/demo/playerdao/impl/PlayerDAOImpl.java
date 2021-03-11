package com.demo.playerdao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.demo.dbutil.PostgresConnection;
import com.demo.model.Player;
import com.demo.playerdao.PlayerDAO;

public class PlayerDAOImpl implements PlayerDAO {

	@Override
	public int addPlayer(Player player) {

		
		int c=0;
		//try with resources available from java 1.7 and above
		//this block doesnt require finally to release the connection, the jvm will take care of releasing the connection
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "insert into player_schema.player(id,name,age,city,gender) values(?,?,?,?,?)";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, player.getId());
			preparedStatement.setString(2, player.getName());
			preparedStatement.setInt(3, player.getAge());
			preparedStatement.setString(4, player.getCity());
			preparedStatement.setString(5, player.getGender());
			c=preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
		
		return c;
	}

	@Override
	public int updatePlayerCity(int id, String newCity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletePlayerById(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Player getPlayerById(int id) {
		Player player=null;
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "select name,age,gender,city from player_schema.player where id=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				player=new Player();
				player.setId(id);
				player.setAge(resultSet.getInt("age"));
				player.setCity(resultSet.getString("city"));
				player.setGender(resultSet.getString("gender"));
				player.setName(resultSet.getString("name"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
		return player;
	}

	@Override
	public List<Player> getAllPlayers() {
		List<Player> playerList=new ArrayList<>();
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "select id,name,age,gender,city from player_schema.player";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			
			
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Player player=new Player();
				player.setId(resultSet.getInt("id"));
				player.setAge(resultSet.getInt("age"));
				player.setCity(resultSet.getString("city"));
				player.setGender(resultSet.getString("gender"));
				player.setName(resultSet.getString("name"));
				playerList.add(player);
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
		return playerList;
	}

	@Override
	public List<Player> getAllPlayersByAge(int age) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Player> getAllPlayersByTeamName(String teamName) {
		List<Player> playerList=new ArrayList<>();
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "select id,name,age,gender,city from player_schema.player p join player_schema.team t on p.teamid=t.teamid where t.teamname=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, teamName);
			
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Player player=new Player();
				player.setId(resultSet.getInt("id"));
				player.setAge(resultSet.getInt("age"));
				player.setCity(resultSet.getString("city"));
				player.setGender(resultSet.getString("gender"));
				player.setName(resultSet.getString("name"));
				playerList.add(player);
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
		return playerList;
	}

	@Override
	public List<Player> getAllPlayersByTeamLocation(String location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Player> getAllPlayersByTeamId(String teamid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Player> getAllPlayersByCity(String city) {
		// TODO Auto-generated method stub
		return null;
	}

}
