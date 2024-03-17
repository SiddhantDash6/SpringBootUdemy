package com.chospital;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class Patient {
	
	private Connection connection;
	private Scanner scanner;
	
	public Patient(Connection connection, Scanner scanner) {
		this.scanner = scanner;
		this.connection = connection;
	}
	
	public void addPatient() {
		System.out.println("Enter Patient Name...");
		String name = scanner.next();
		
		System.out.println("Enter Patient Age...");
		int age = scanner.nextInt();
		
		System.out.println("Enter Patient Gender...");
		String gender = scanner.next();
		
		try {
			String query = "INSERT INTO patients(name, age, gender) VALUES(?, ?, ?)";
			PreparedStatement prepare = connection.prepareStatement(query);
			prepare.setString(1, name);
			prepare.setInt(2, age);
			prepare.setString(3, gender);
			
			int affectedRows = prepare.executeUpdate();
			
			if(affectedRows > 0) {
				System.out.println("Patient added successfully");
			} else {
				System.out.println("Failed to add patient");
			}
			
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void viewPatient() {
		String query = "SELECT * FROM patients";
		try {
			
			PreparedStatement prepare = connection.prepareStatement(query);
			ResultSet rs = prepare.executeQuery();
			System.out.println("patients: ");
			System.out.println("+------------+-------------------+-------------+------------+");
			System.out.println("| Patient Id | Name              | Age         | Gender     |");
			System.out.println("+------------+-------------------+-------------+------------+");
			while(rs.next()) {
				int id = rs.getInt("pid");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				System.out.printf("|%-12s|%-20s|%-13s|%-12s|\n", id, name, age, gender);
				System.out.println("+------------+-------------------+-------------+------------+");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean getPatientById(int id) {
		String query = "SELECT * FROM patients WHERE pid = ?";
		try {
			PreparedStatement prepare = connection.prepareStatement(query);
			prepare.setInt(1, id);
			ResultSet rs = prepare.executeQuery();
			if(rs.next()) {
				return true;
			} else {
				return false;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
