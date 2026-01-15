import { use, useEffect } from "react";
import "./catalogo.css";
import {hotelService} from "../../../services/hotelService.js";
import { useState } from "react";
import React from "react";

const inicialFilters = {
    nombre: "",
    ciudad: "",
    categoria: "",
    precioMin: null,
    precioMax: null,
    servicios: [],
    page: 1,
    limit: 10,
    orderby: ""
}

export const Catalogo = () => {
    const [hoteles, setHoteles] = useState([]);
    const [pageInfo, setPageInfo] = useState({});
    const [filters, setFilters] = useState(inicialFilters);
    const [appliedFilters, setAppliedFilters] = useState(inicialFilters);
    const [filterValidationError, setFilterValidationError] = useState("");

    /* Hooks */
    useEffect(() => {
        hotelService.getAll({ page: 0, size: 10, sort: "precio_asc" })
            .then(data => {
            setHoteles(data.content);
            setPageInfo(data);
            })
            .catch(err => console.error("ERROR:", err));
    }, []);

    /* Funciones auxiliares */
    const handleSubmit = (e) => {
        e.preventDefault();
        applyFilters();
    }

    const handleClearFiltros = () => {
        setFilters(inicialFilters);
        setAppliedFilters(inicialFilters);
        setFilterValidationError("");
    }

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFilters(
            (prev) => ({...prev, [name]: value, page: 1})
        )
    }

    const handleLimitChange = (e) => {
        setFilters(
            (prev) => ({...prev, limit: Number(e.target.value), page: 1})
        )
    }

    const applyFilters = () => {
        setFilterValidationError("");
        setAppliedFilters({ ...filters, page: 1 });
    }
    
    return (
      <>
        <h2 style={{ color: "#2d2d2d", marginBottom: "1.5rem" }}>
            Explorar Hoteles
        </h2>
        <div className="hoteles-page">
            <aside>
                <form onSubmit={handleSubmit}>
                    <div className="input-group mb-3">
                        <span className="input-group-text" id="basic-addon1">
                            Nombre
                        </span>
                        <input
                            type="text"
                            className="form-control"
                            name="nombre"
                            placeholder="hotel"
                            aria-label="Hotel"
                            aria-describedby="basic-addon1"
                            value={filters.nombre}
                            onChange={handleChange}
                        />
                    </div>

                    <div className="input-group mb-3">
                        <span className="input-group-text" id="basic-addon1">
                            Ciudad
                        </span>
                        <input
                            type="text"
                            className="form-control"
                            name="ciudad"
                            placeholder="ciudad"
                            aria-label="Ciudad"
                            aria-describedby="basic-addon1"
                            value={filters.ciudad}
                            onChange={handleChange}
                        />
                    </div>

                    <div className="input-group mb-3">
                        <span className="input-group-text" id="basic-addon1">
                            Categoria
                        </span>
                        <input
                            type="text"
                            className="form-control"
                            name="categoria"
                            placeholder="categoria"
                            aria-label="Categoria"
                            aria-describedby="basic-addon1"
                            value={filters.categoria}
                            onChange={handleChange}
                        />
                    </div>

                    <div className="input-group mb-3">
                        <span className="input-group-text" id="basic-addon1">
                            Precio min.
                        </span>
                        <input
                            type="number"
                            className="form-control"
                            name="precioMin"
                            placeholder="precio minimo."
                            aria-label="Precio minimo."
                            aria-describedby="basic-addon1"
                            min={0}
                            value={filters.precioMin}
                            onChange={handleChange}
                        />
                    </div>

                    <div className="input-group mb-3">
                        <span className="input-group-text" id="basic-addon1">
                            Precio max.
                        </span>
                        <input
                            type="number"
                            className="form-control"
                            name="precioMax"
                            placeholder="precio maximo."
                            aria-label="Precio maximo."
                            aria-describedby="basic-addon1"
                            min={0}
                            value={filters.precioMax}
                            onChange={handleChange}
                        />
                    </div>
                    <div className="input-group mb-3">
                        <span className="input-group-text" id="basic-addon1">
                            Cant. hoteles
                        </span>
                        <input
                            type="number"
                            className="form-control"
                            name="limit"
                            placeholder="Cantidad de hoteles"
                            aria-label="Cantidad de hoteles"
                            aria-describedby="basic-addon1"
                            value={filters.limit}
                            onChange={handleLimitChange}
                            min={1}
                        />
                    </div>
                    <div className="filter-section" style={{ display: "flex", gap: 8 }}>
                        <button type="submit" className="btn btn-primary">
                            Buscar
                        </button>
                        <button 
                        type="button" 
                        className="btn btn-secondary mt-3"
                        onClick={handleClearFiltros}>
                            Limpiar filtros
                        </button>
                    </div>
                    {filterValidationError && (
                        <p
                            className="status-message error-message"
                            style={{ marginTop: 8 }}
                        >
                            {filterValidationError}
                        </p>
                    )}
                </form>
            </aside>
            <main className="main-content">
                
            </main>
        </div>
      </>
    );
};

