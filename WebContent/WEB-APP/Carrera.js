class Carrera extends CRUDGenerico {
    constructor() {
        super('carreras');
        this.listaCarrera = document.querySelector('.carreras-container__lista');
    }

    mostrarCarreras(carreras) {
        this.listaCarrera.innerHTML = '<ul>';

        carreras.forEach(c => {
            this.listaCarrera.innerHTML += `<li>${c.nombre}</li>`;
        });

        this.listaCarrera.innerHTML += '</ul>';
    }

	getReporte(filtro){
		this.getModular(`/reporte?filtro=${filtro}`).then( response => this.mostrarReporte(response));
	}
	
	getCarreraId(id){
		this.getModular(`/${id}`).then( response => this.mostrarReporte(response));
	}
	
	getCarreraEstudiantes(id){
		if(id === ''){
			alert('Ingresa un id');
		}
		else {
			this.getModular(`/${id}/estudiantes?ciudad=Bolivar"`).then( response => this.mostrarReporte(response));			
		}
	}
	
	mostrarReporte(reporte){
		console.log(reporte);
	}
}