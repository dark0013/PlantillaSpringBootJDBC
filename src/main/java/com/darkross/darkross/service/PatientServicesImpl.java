package com.darkross.darkross.service;

import com.darkross.darkross.model.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
@Service
@RequiredArgsConstructor
public class PatientServicesImpl implements IPatientService{
    private final JdbcTemplate jdbcTemplate;
    @Override
    public List<Patient> findAll() {
        String sql = "SELECT * FROM patients"; // Reemplaza 'patients' con el nombre de tu tabla de pacientes
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapPatientFromResultSet(rs));
    }

    private Patient mapPatientFromResultSet(ResultSet rs) throws SQLException {
        Patient patient = new Patient();
        patient.setIdpatient(rs.getInt("idpatient"));
        patient.setFirstname(rs.getString("firstname"));
        patient.setLastname(rs.getString("lastname"));
        patient.setDni(rs.getString("dni"));
        patient.setAddress(rs.getString("address"));
        patient.setPhone(rs.getString("phone"));
        patient.setEmail(rs.getString("email"));
        return patient;
    }


    @Override
    public Patient findById(Integer id) {
        String sql = "SELECT * FROM patients WHERE idpatient = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> mapPatientFromResultSet(rs));
    }

    public Patient save(Patient patient) {
        String sql = "INSERT INTO patients (firstname, lastname, dni, address, phone, email)" +
                "VALUES (?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, patient.getFirstname());
            ps.setString(2, patient.getLastname());
            ps.setString(3, patient.getDni());
            ps.setString(4, patient.getAddress());
            ps.setString(5, patient.getPhone());
            ps.setString(6, patient.getEmail());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            int generatedId = keyHolder.getKey().intValue();
            patient.setIdpatient(generatedId);
        } else {
            // Manejar el caso en el que no se generó un ID (por ejemplo, lanzar una excepción)
            throw new RuntimeException("No se generó un ID para el paciente.");
        }

        return patient;
    }

    @Override
    public Patient update(Patient patient) {
        String sql = "UPDATE patients " +
                        "SET firstname = ?, lastname = ?,dni = ?,address = ?,phone = ?,email = ? " +
                      "WHERE idpatient = ?";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, patient.getFirstname());
            ps.setString(2, patient.getLastname());
            ps.setString(3, patient.getDni());
            ps.setString(4, patient.getAddress());
            ps.setString(5, patient.getPhone());
            ps.setString(6, patient.getEmail());
            ps.setInt(7, patient.getIdpatient());
            return ps;
        });
        return patient;
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM patients WHERE idpatient = ?";

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            return ps;
        });
    }

}
