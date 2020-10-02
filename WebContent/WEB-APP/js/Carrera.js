class Carrera extends CRUDGenerico {
    constructor() {
        super('carreras');
        this.listaCarrera = document.querySelector('.main');
    }

    mostrarCarreras(carreras) {
        this.listaCarrera.innerHTML = '<ul>';

        carreras.forEach(c => {
            this.listaCarrera.innerHTML += `<li>${c.nombre}</li>`;
        });

        this.listaCarrera.innerHTML += '</ul>';
    }

	getReporte(filtro){
		this.getModular(`/reporte?filtro=${filtro}`).then( response => this.mostrarReporte(response))
													.catch(err => alert(err));
	}
	
	getCarreraId(id){
		this.getModular(`/${id}`).then( response => this.mostrarReporte(response))
								 .catch(err => alert(err));
	}
	
	getCarreraEstudiantes(id){
		if(id === ''){
			alert('Ingresa un id');
		}
		else {
			this.getModular(`/${id}/estudiantes?ciudad=Bolivar"`).then( response => this.mostrarReporte(response))
																 .catch(err => alert(err));			
		}
	}
	
	mostrarReporte(reporte){
		console.log(reporte);
	}
}