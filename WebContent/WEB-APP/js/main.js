"use strict"

document.addEventListener("DOMContentLoaded", () => {
    const ESTUDIANTE = new Estudiante();
    const CARRERA = new Carrera();
    const MATRICULA = new Matricula();
    const selectCarreras = document.querySelector('#selectCarreras');
    const container = document.querySelector('.main');
    const modalLibreta = $('#modalLibreta');
    const modalCarreraId = $('#modalCarreraId');
	const modalAgregarCarrera = $('#modalAgregarCarrera');
	const modalEstudiantesCarrera = $('#modalEstudiantesCarrera');
    traerCarreras();

    document.querySelector('.estudiantes-container__btnLibreta').addEventListener('click', () => {
        pedirNroLibreta();
    });

    document.querySelector('.estudiantes-container__btnBuscar').addEventListener('click', () => {
        const numero = document.querySelector('.estudiantes-container__numeroLibreta').value;

        if (numero > 0 && numero != '') {
            ESTUDIANTE.getEstudianteLibreta(numero);
            modalLibreta.modal('hide');
        } else
            alert('Ingrese un número');
    });

    document.querySelector('.estudiantes-container__btn').addEventListener('click', () => {
        ESTUDIANTE.getAll()
            .then(estudiantes => ESTUDIANTE.mostrarEstudiantes(estudiantes));
    });

    document.querySelectorAll('.estudiantes-container__btnGenero').forEach(btn => {
        btn.addEventListener('click', () => {
            const genero = btn.getAttribute('data-genero');
            ESTUDIANTE.getEstudiantesGenero(genero);
        });
    });

    document.querySelector('.carreras-container__btn').addEventListener('click', () => {
        CARRERA.getAll()
            .then(carreras => CARRERA.mostrarCarreras(carreras));
    });

    document.querySelector('.carreras-container__btnBuscarId').addEventListener('click', () => {
        pedirIdCarrera();
    });

	document.querySelector('.carreras-container__btnAgregarModal').addEventListener('click', () => {
        agregarCarrera();
    });

	document.querySelector('.carreras-container__btnEstudiantesCarreraModal').addEventListener('click', () => {
        obtenerEstudiantesCarrera();
    });

    document.querySelector('.carreras-container__btnAgregar').addEventListener('click', () => {
        const nombre = document.querySelector('.carreras-container__inputCarrera').value;

		if(nombre != '') {
	        let data = {
	            "nombre": nombre
	        };
	
	        CARRERA.add(data)
	            .then(() => {
					alert('Carrera agregada');
					modalAgregarCarrera.modal('hide');
				})
	            .catch(err => alert(err));			
		}
		else
			alert('Ingrese un nombre');
    });

    document.querySelector('.carreras-container__btnCarreraId').addEventListener('click', () => {
        const id = document.querySelector('.carreras-container__input').value;
		if(id > 0 && id != '') {
        	CARRERA.getCarreraId(id);
			modalCarreraId.modal('hide');			
		}
		else
			alert('Ingrese un id');
    });

    document.querySelector('.carreras-container__btnCarreraEstudiantes').addEventListener('click', () => {
        CARRERA.getCarreraEstudiantes(selectCarreras.value);
		modalEstudiantesCarrera.modal('hide');
    });

    document.querySelector('.carreras-container__btnReporte').addEventListener('click', () => {
        CARRERA.getReporte('inscriptos');
    });

    document.querySelector('.matriculas-container__btn').addEventListener('click', () => {
        MATRICULA.getAll()
            .then(matriculas => MATRICULA.mostrarMatriculas(matriculas));
    });

    //	document.querySelector('.matriculas-container__btnMatricular').addEventListener('click', () => {
    //        MATRICULA.matricularEstudiante(2, 1);
    //    });

    document.querySelector('.matriculas-container__btnReporte').addEventListener('click', () => {
        MATRICULA.getReporte();
    });

    function traerCarreras() {
        CARRERA.getAll()
            .then(carreras => {
                carreras.forEach(carr => {
                    selectCarreras.innerHTML += `<option value="${carr.id}">${carr.nombre}</option>`;
                });
            });
    }

    function pedirNroLibreta() {
        modalLibreta.modal('show');
    }

    function pedirIdCarrera() {
        modalCarreraId.modal('show');
    }

	function agregarCarrera() {
        modalAgregarCarrera.modal('show');
    }

	function obtenerEstudiantesCarrera() {
		modalEstudiantesCarrera.modal('show');
	}
});