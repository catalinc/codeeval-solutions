import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Solution to https://www.codeeval.com/open_challenges/221/
 */
public class OrganizationalHierarchy {

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
      String line;
      while ((line = reader.readLine()) != null) {
        Employee ceo = parseHierarchy(line);
        printHierarchy(ceo);
      }
    }
  }

  private static Employee parseHierarchy(String input) {
    String[] tokens = input.split(" \\| ");
    Employee ceo = new Employee(' ');
    for (int i = 0; i < tokens.length; i++) {
      String t = tokens[i];
      for (int j = 0; j < t.length(); j++) {
        char bossInitial = t.charAt(0);
        char subordinateInitial = t.charAt(1);
        Employee boss = ceo.getSubordinate(bossInitial);
        if (boss == null) {
          boss = ceo.addSubordinate(bossInitial);
        }
        Employee subordinate = ceo.getSubordinate(subordinateInitial);
        if (subordinate == null) {
          subordinate = ceo.addSubordinate(subordinateInitial);
        }
        boss.addSubordinate(subordinate);
      }
    }
    return ceo;
  }

  private static void printHierarchy(Employee employee) {
    StringBuilder buffer = new StringBuilder();
    HashSet<Employee> visited = new HashSet<>();

    printHierarchy(employee, visited, buffer);

    System.out.println(buffer);
  }

  private static void printHierarchy(Employee employee, HashSet<Employee> visited, StringBuilder buffer) {
    if (visited.contains(employee)) return;
    visited.add(employee);

    if (!employee.isCeo()) {
      buffer.append(employee.getInitial());
    }

    if (employee.hasSubordinates()) {
      if (!employee.isCeo()) {
        buffer.append(' ');
        buffer.append('[');
      }
      Iterator<Employee> subordinates = employee.getSubordinates().iterator();
      while (subordinates.hasNext()) {
        Employee subordinate = subordinates.next();
        if (!visited.contains(subordinate)) {
          printHierarchy(subordinate, visited, buffer);
          if (subordinates.hasNext()) {
            if (!employee.isCeo()) {
              buffer.append(',');
              buffer.append(' ');
            }
          }
        }
      }
      if (!employee.isCeo()) {
        buffer.append(']');
      }
    }
  }

  private static class Employee implements Comparable<Employee> {
    private final Character initial;
    private final SortedMap<Character, Employee> subordinates;

    private Employee(Character initial) {
      this.initial = initial;
      this.subordinates = new TreeMap<>();
    }

    private Character getInitial() {
      return initial;
    }

    private Collection<Employee> getSubordinates() {
      return subordinates.values();
    }

    private Employee addSubordinate(char initial) {
      Employee employee = new Employee(initial);
      subordinates.put(initial, employee);
      return employee;
    }

    private Employee getSubordinate(char initial) {
      return subordinates.get(initial);
    }

    private void addSubordinate(Employee subordinate) {
      subordinates.put(subordinate.initial, subordinate);
    }

    private boolean hasSubordinates() {
      return !subordinates.isEmpty();
    }

    private boolean isCeo() {
      return initial == ' ';
    }

    @Override
    public int compareTo(Employee o) {
      return initial.compareTo(o.initial);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Employee employee = (Employee) o;

      return initial.equals(employee.initial);
    }

    @Override
    public int hashCode() {
      return initial.hashCode();
    }
  }

}
