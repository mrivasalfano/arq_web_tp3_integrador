"use strict"

document.addEventListener("DOMContentLoaded", () => {
    const ESTUDIANTE = new Estudiante();
    const CARRERA = new Carrera();
    const MATRICULA = new Matricula();

    document.querySelector('.estudiantes-container__btn').addEventListener('click', () => {
        ESTUDIANTE.getAll()
            .then(estudiantes => ESTUDIANTE.mostrarEstudiantes(estudiantes));
    });

	document.querySelector('.estudiantes-container__btnGenero').addEventListener('click', () => {
        const genero = document.querySelector('.estudiantes-container__numeroLibreta').value;
        ESTUDIANTE.getEstudiantesGenero(genero);
    });


    document.querySelector('.estudiantes-container__btnBuscar').addEventListener('click', () => {
        const numero = document.querySelector('.estudiantes-container__numeroLibreta').value;
        ESTUDIANTE.getEstudianteLibreta(numero);
    });

    document.querySelector('.carreras-container__btn').addEventListener('click', () => {
        CARRERA.getAll()
            .then(carreras => CARRERA.mostrarCarreras(carreras));
    });

    document.querySelector('.carreras-container__btnAgregar').addEventListener('click', () => {
        const nombre = document.querySelector('.carreras-container__input').value;
        let data = {
            "nombre": nombre
        };

        CARRERA.add(data);
    });

	document.querySelector('.carreras-container__btnCarreraId').addEventListener('click', () => {
        const id = document.querySelector('.carreras-container__input').value;
		CARRERA.getCarreraId(id);
    });

	document.querySelector('.carreras-container__btnCarreraEstudiantes').addEventListener('click', () => {
        const id = document.querySelector('.carreras-container__input').value;
		CARRERA.getCarreraEstudiantes(id);
    });

	document.querySelector('.carreras-container__btnReporte').addEventListener('click', () => {
		const filtro = document.querySelector('.carreras-container__input').value;
        CARRERA.getReporte(filtro);
    });

    document.querySelector('.matriculas-container__btn').addEventListener('click', () => {
        MATRICULA.getAll()
            .then(matriculas => MATRICULA.mostrarMatriculas(matriculas));
    });

	document.querySelector('.matriculas-container__btnMatricular').addEventListener('click', () => {
        MATRICULA.matricularEstudiante(2, 1);
    });

	document.querySelector('.matriculas-container__btnReporte').addEventListener('click', () => {
        MATRICULA.getReporte();
    });
});