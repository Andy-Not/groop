import React, { createContext, useState } from "react";
import { useLocalStorage } from "../hooks/useLocalStorage";

export const GlobalSwimLaneStateContext = createContext();

export const GlobalSwimLaneStateProvider = ({ children }) => {
  const [laneData] = useLocalStorage({}, "swimLane");
  const [currentSwimLane, setCurrentSwimLane] = useState(laneData);

  return (
    <GlobalSwimLaneStateContext.Provider
      value={[currentSwimLane, setCurrentSwimLane]}
    >
      {children}
    </GlobalSwimLaneStateContext.Provider>
  );
};
