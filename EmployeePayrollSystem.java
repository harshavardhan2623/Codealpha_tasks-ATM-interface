import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Employee {
    private String name;
    private double hourlyRate;
    private int hoursWorked;

    public Employee(String name, double hourlyRate) {
        this.name = name;
        this.hourlyRate = hourlyRate;
        this.hoursWorked = 0;
    }

    public String getName() {
        return name;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public double calculateSalary() {
        return hourlyRate * hoursWorked;
    }
}

class PayrollSystem {
    private List<Employee> employees;

    public PayrollSystem() {
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}

public class EmployeePayrollSystem {
    private PayrollSystem payrollSystem = new PayrollSystem();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            new EmployeePayrollSystem().createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Employee Payroll System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton addEmployeeButton = new JButton("Add Employee");
        JButton generatePayrollButton = new JButton("Generate Payroll");

        JTextArea payrollTextArea = new JTextArea(20, 40);

        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(frame, "Enter employee name:");
                double hourlyRate = Double.parseDouble(JOptionPane.showInputDialog(frame, "Enter hourly rate:"));

                Employee employee = new Employee(name, hourlyRate);
                payrollSystem.addEmployee(employee);

                JOptionPane.showMessageDialog(frame, "Employee added successfully!");
            }
        });

        generatePayrollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder payStubs = new StringBuilder("Payroll Summary:\n");

                for (Employee employee : payrollSystem.getEmployees()) {
                    int hoursWorked = Integer.parseInt(JOptionPane.showInputDialog(frame,
                            "Enter hours worked for " + employee.getName() + ":"));
                    employee.setHoursWorked(hoursWorked);

                    payStubs.append("Employee: ").append(employee.getName())
                            .append(", Salary: $").append(employee.calculateSalary())
                            .append("\n");
                }

                payrollTextArea.setText(payStubs.toString());
            }
        });

        JPanel panel = new JPanel();
        panel.add(addEmployeeButton);
        panel.add(generatePayrollButton);

        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, new JScrollPane(payrollTextArea));

        frame.setSize(500, 400);
        frame.setVisible(true);
    }
}
