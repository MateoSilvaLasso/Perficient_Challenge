import { createStore } from 'redux';

const initialState = {
  variableGlobal: 'valorInicial',
};

const cambiarVariableGlobal = (nuevoValor) => {
    return {
      type: 'CAMBIAR_VARIABLE_GLOBAL',
      payload: nuevoValor,
    };
  };

const store = createStore(() => {}, initialState);

export default store