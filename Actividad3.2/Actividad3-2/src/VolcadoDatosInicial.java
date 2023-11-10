public class VolcadoDatosInicial {
    public static void main(String[] args) {
        // Insertar datos en la tabla departamento
        DepartamentoDAO.crearDepartamento("Desarrollo", 120000, 6000);
        DepartamentoDAO.crearDepartamento("Sistemas", 150000, 21000);
        DepartamentoDAO.crearDepartamento("Recursos Humanos", 280000, 25000);
        DepartamentoDAO.crearDepartamento("Contabilidad", 110000, 3000);
        DepartamentoDAO.crearDepartamento("I+D", 375000, 380000);
        DepartamentoDAO.crearDepartamento("Proyectos", 0, 0);
        DepartamentoDAO.crearDepartamento("Publicidad", 0, 1000);

        // Insertar datos en la tabla empleado
        EmpleadoDAO.crearEmpleado("32481596F", "Aarón", "Rivero", "Gómez", 1);
        EmpleadoDAO.crearEmpleado("Y5575632D", "Adela", "Salas", "Díaz", 2);
        EmpleadoDAO.crearEmpleado("R6970642B", "Adolfo", "Rubio", "Flores", 3);
        EmpleadoDAO.crearEmpleado("77705545E", "Adrián", "Suárez", null, 4);
        EmpleadoDAO.crearEmpleado("17087203C", "Marcos", "Loyola", "Méndez", 5);
        EmpleadoDAO.crearEmpleado("38382980M", "María", "Santana", "Moreno", 1);
        EmpleadoDAO.crearEmpleado("80576669X", "Pilar", "Ruiz", null, 2);
        EmpleadoDAO.crearEmpleado("71651431Z", "Pepe", "Ruiz", "Santana", 3);
        EmpleadoDAO.crearEmpleado("56399183D", "Juan", "Gómez", "López", 2);
        EmpleadoDAO.crearEmpleado("46384486H", "Diego", "Flores", "Salas", 5);
        EmpleadoDAO.crearEmpleado("67389283A", "Marta", "Herrera", "Gil", 1);
        EmpleadoDAO.crearEmpleado("41234836R", "Irene", "Salas", "Flores", 0);
        EmpleadoDAO.crearEmpleado("82635162B", "Juan Antonio", "Sáez", "Guerrero", 0);
    }
}
