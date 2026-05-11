import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import conec.CONECTA;

public class ConsultasBD {

    // INGRESOS

    public static LinkedList<Ingreso> getIngresos(String cod_use) {
        LinkedList<Ingreso> lista = new LinkedList<>();
        try {
            Connection con = CONECTA.conectar();
            String sql = "SELECT * FROM ingresos WHERE \"USU_CODIGO\" = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, java.util.UUID.fromString(cod_use));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Ingreso ing = new Ingreso(
                    rs.getString("ING_CODIGO"),
                    rs.getString("USU_CODIGO"),
                    rs.getDouble("ING_MONTO"),
                    rs.getString("ING_DESCRIPCION"),
                    rs.getDate("ING_FECHA")
                );
                lista.add(ing);
            }
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public static double getTotalIngresos(String cod_use) {
        try {
            Connection con = CONECTA.conectar();
            String sql = "SELECT COALESCE(SUM(\"ING_MONTO\"), 0) AS total FROM ingresos WHERE \"USU_CODIGO\" = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, java.util.UUID.fromString(cod_use));
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getDouble("total");
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static boolean guardarIngreso(Ingreso ing) {
        try {
            Connection con = CONECTA.conectar();
            String sql = "INSERT INTO ingresos (\"USU_CODIGO\", \"ING_MONTO\", \"ING_DESCRIPCION\", \"ING_FECHA\") VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setObject(1, java.util.UUID.fromString(ing.getUsu_codigo()));
            ps.setDouble(2, ing.getIng_monto());
            ps.setString(3, ing.getIng_descripcion());
            ps.setDate(4, ing.getIng_fecha());

            ps.executeUpdate();
            con.close();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean eliminarIngreso(String ing_codigo) {
        try {
            Connection con = CONECTA.conectar();
            String sql = "DELETE FROM ingresos WHERE \"ING_CODIGO\" = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setObject(1, java.util.UUID.fromString(ing_codigo));
            ps.executeUpdate();

            con.close();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // GASTOS

    public static LinkedList<Gasto> getGastos(String cod_use) {
        LinkedList<Gasto> lista = new LinkedList<>();
        try {
            Connection con = CONECTA.conectar();
            String sql = "SELECT * FROM gastos WHERE \"USU_CODIGO\" = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, java.util.UUID.fromString(cod_use));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Gasto gas = new Gasto(
                    rs.getString("GAS_CODIGO"),
                    rs.getString("USU_CODIGO"),
                    rs.getDouble("GAS_MONTO"),
                    rs.getString("GAS_CATEGORIA"),
                    rs.getString("GAS_DESCRIPCION"),
                    rs.getDate("GAS_FECHA"),
                    rs.getString("APA_CODIGO")
                );
                lista.add(gas);
            }
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public static double getTotalGastos(String cod_use) {
        try {
            Connection con = CONECTA.conectar();
            String sql = "SELECT COALESCE(SUM(\"GAS_MONTO\"), 0) AS total FROM gastos WHERE \"USU_CODIGO\" = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, java.util.UUID.fromString(cod_use));
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getDouble("total");
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static boolean guardarGasto(Gasto gas) {
        try {
            Connection con = CONECTA.conectar();
            String sql = "INSERT INTO gastos (\"USU_CODIGO\", \"GAS_MONTO\", \"GAS_CATEGORIA\", \"GAS_DESCRIPCION\", \"GAS_FECHA\", \"APA_CODIGO\") VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setObject(1, java.util.UUID.fromString(gas.getUsu_codigo()));
            ps.setDouble(2, gas.getGas_monto());
            ps.setString(3, gas.getGas_categoria());
            ps.setString(4, gas.getGas_descripcion());
            ps.setDate(5, gas.getGas_fecha());
            ps.setObject(6, java.util.UUID.fromString(gas.getApa_codigo()));

            ps.executeUpdate();
            con.close();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean eliminarGasto(String gas_codigo) {
        try {
            Connection con = CONECTA.conectar();
            String sql = "DELETE FROM gastos WHERE \"GAS_CODIGO\" = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setObject(1, java.util.UUID.fromString(gas_codigo));
            ps.executeUpdate();

            con.close();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // APARTADOS

    public static LinkedList<Apartado> getApartados(String cod_use) {
        LinkedList<Apartado> lista = new LinkedList<>();
        try {
            Connection con = CONECTA.conectar();
            String sql = "SELECT * FROM apartado WHERE \"USU_CODIGO\" = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setObject(1, java.util.UUID.fromString(cod_use));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Apartado apa = new Apartado(
                    rs.getString("APA_CODIGO"),
                    rs.getString("USU_CODIGO"),
                    rs.getDouble("APA_LIMITE"),
                    rs.getString("APA_CATEGORIA"),
                    rs.getDate("APA_FECHA")
                );
                lista.add(apa);
            }

            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public static boolean guardarApartado(Apartado apa) {
        try {
            Connection con = CONECTA.conectar();
            String sql = "INSERT INTO apartado (\"USU_CODIGO\", \"APA_LIMITE\", \"APA_CATEGORIA\", \"APA_FECHA\") VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setObject(1, java.util.UUID.fromString(apa.getUsu_codigo()));
            ps.setDouble(2, apa.getApa_limite());
            ps.setString(3, apa.getApa_categoria());
            ps.setDate(4, apa.getApa_fecha());

            ps.executeUpdate();
            con.close();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean eliminarApartado(String apa_codigo) {
        try {
            Connection con = CONECTA.conectar();
            String sql = "DELETE FROM apartado WHERE \"APA_CODIGO\" = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setObject(1, java.util.UUID.fromString(apa_codigo));
            ps.executeUpdate();

            con.close();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // PRESUPUESTO

    public static LinkedList<Presupuesto> getPresupuestos(String cod_use) {
        LinkedList<Presupuesto> lista = new LinkedList<>();
        try {
            Connection con = CONECTA.conectar();
            String sql = "SELECT * FROM presupuesto WHERE \"USU_CODIGO\" = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setObject(1, java.util.UUID.fromString(cod_use));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Presupuesto pre = new Presupuesto(
                    rs.getString("PRE_CODIGO"),
                    rs.getString("USU_CODIGO"),
                    rs.getDouble("PRE_MONTO_TOTAL"),
                    rs.getDate("PRE_FECHA")
                );
                lista.add(pre);
            }

            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public static double getTotalPresupuesto(String cod_use) {
        try {
            Connection con = CONECTA.conectar();
            String sql = "SELECT COALESCE(SUM(\"PRE_MONTO_TOTAL\"), 0) AS total FROM presupuesto WHERE \"USU_CODIGO\" = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setObject(1, java.util.UUID.fromString(cod_use));
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getDouble("total");
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static boolean guardarPresupuesto(Presupuesto pre) {
        try {
            Connection con = CONECTA.conectar();
            String sql = "INSERT INTO presupuesto (\"USU_CODIGO\", \"PRE_MONTO_TOTAL\", \"PRE_FECHA\") VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setObject(1, java.util.UUID.fromString(pre.getUsu_codigo()));
            ps.setDouble(2, pre.getPre_monto_total());
            ps.setDate(3, pre.getPre_fecha());

            ps.executeUpdate();
            con.close();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean eliminarPresupuesto(String pre_codigo) {
        try {
            Connection con = CONECTA.conectar();
            String sql = "DELETE FROM presupuesto WHERE \"PRE_CODIGO\" = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setObject(1, java.util.UUID.fromString(pre_codigo));
            ps.executeUpdate();

            con.close();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // SALDO

    public static double getSaldo(String cod_use) {
        return getTotalPresupuesto(cod_use) + getTotalIngresos(cod_use) - getTotalGastos(cod_use);
    }
}