class CRUDGenerico {
    constructor(resource) {
        this.RESOURCE = resource;
        this.BASEURI = 'http://localhost:8080/arq_web_tp3_integrador/rest';
    }

    async add(data) {
        await fetch(this.BASEURI + '/' + this.RESOURCE, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-type': 'application/json'
            },
            body: JSON.stringify(data)
        });
    }

    async getAll() {
        const response = await fetch(this.BASEURI + "/" + this.RESOURCE);
        return await response.json();
    }

	async getModular(uri) {
	        const response = await fetch(this.BASEURI + "/" + this.RESOURCE + uri);
	        return await response.json();
	}
	
	async postModular(uri, data) {
        await fetch(this.BASEURI + '/' + this.RESOURCE + uri, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-type': 'application/json'
            },
            body: JSON.stringify(data)
        });
    }
}