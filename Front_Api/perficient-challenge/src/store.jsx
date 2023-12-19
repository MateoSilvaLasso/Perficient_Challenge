import { createStore } from 'redux';

const initialState = {
  variableGlobal: 'valorInicial',
};

function counterReducer(state = initialState, action) {

      return { variableGlobal: action };
}

const store = createStore(counterReducer);

export default store