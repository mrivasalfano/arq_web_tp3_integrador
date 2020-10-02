class Carrera extends CRUDGenerico {
    constructor() {
        super('carreras');
        this.container = document.querySelector('.main');
    }

    mostrarCarreras(carreras) {
		let filas = '';
		
		carreras.forEach(carr => {
			filas += `	<tr>
							<td>${carr.id}</td>
							<td>${carr.nombre}</td>
						</tr>`;
		});
		
        this.container.innerHTML = 
			`<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">id</th>
							<th scope="col">Nombre</th>
						</tr>
					</thead>
					<tbody class="bg-light">
						${filas}
					</tbody>
				</table>`;
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
}