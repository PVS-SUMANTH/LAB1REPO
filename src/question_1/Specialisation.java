package question_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Specialisation {

	public static void main(String[] args) throws SQLException {

		// creation of tables
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kluniversity", "root",
					"saisum@2");
//			String query0 = "create table specialization(ID integer primary key,sname varchar(30) not null,slots integer not null)";
//			String query1 = "create table specializationreg(ID integer primary key,name varchar(30) not null,specialization varchar(30) not null)";
			Statement stmt = con.createStatement();
//			stmt.executeUpdate(query0);
//			stmt.executeUpdate(query1);
			Scanner sc = new Scanner(System.in);

			System.out.println("Menu\n" + "1.Selecting spec\n 2.Spec Updates\n");
			int key = sc.nextInt();
			switch (key) {
			case 1: {
				String query = "select * from specialization";
				ResultSet rs = stmt.executeQuery(query);
				System.out.println("ID\tSpecialization\tAvailable slots");
				System.out.println("------------------------------------------------------------");
				while (rs.next()) {
					System.out.print(rs.getInt("id") + "\t" + rs.getString("sname") + "\t");
					System.out.println(rs.getInt("slots"));
				}

				System.out.println("select the ID to enroll into the specialization:");
				int opt = sc.nextInt();
				String query2 = " select * from specialization where id = " + opt + "";
				ResultSet rs1 = stmt.executeQuery(query2);
				while (rs1.next()) {
					if (opt == rs1.getInt("id")) {
						int slots = rs1.getInt("slots");
						if (slots == 0)
							System.out.println("SLOTS WERE FILLED");
						else {
							String specialization = rs1.getString("sname");
							System.out.println("Enter your id : ");
							int id = sc.nextInt();
							System.out.println("Enter your name : ");
							String name = sc.next();
							String query3 = "insert into specializationreg values(" + id + ",'" + name + "','"
									+ specialization + "')";
							stmt.executeUpdate(query3);
							slots = slots - 1;
							String query4 = "update specialization set slots = " + slots + " where id = " + opt + "";
							stmt.executeUpdate(query4);
							System.out.println("Sucessfully Registered");
						}

					}
				}

			}
				break;
			case 2: {
				int id;
				String name = null;
				System.out.println("Enter ID:");
				id = sc.nextInt();
				System.out.println("Enter spec name:");
				name = sc.next();
				System.out.println("Enter Slots available:");
				int slots = sc.nextInt();
				String query8 = " Insert into specialization values(" + id + ",'" + name + "'," + slots + ")";
				stmt.executeUpdate(query8);
				System.out.println("Record is inserted");
			}
				break;

			default:
				break;
			}

			sc.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
