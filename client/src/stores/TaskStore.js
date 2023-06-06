import { createContext, useState } from "react";

export const GlobalTaskContext = createContext();

export const GlobalTaskProvider = (props) => {
  const [globalTaskList, setGlobalTaskList] = useState([]);
  return (
    <GlobalTaskContext.Provider value={[globalTaskList, setGlobalTaskList]}>
      {props.children}
    </GlobalTaskContext.Provider>
  );
};
