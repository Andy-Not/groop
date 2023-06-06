import { createContext, useState } from "react";

export const GlobalBoardContext = createContext();

export const GlobalBoardProvider = (props) => {
  const [globalBoardList, setGlobalBoardList] = useState([]);
  return (
    <GlobalBoardContext.Provider value={[globalBoardList, setGlobalBoardList]}>
      {props.children}
    </GlobalBoardContext.Provider>
  );
};
