import React, { createContext, useState } from "react";
export const CurrentUserStateContext = createContext();

export const CurrentUserStateContextProvider = ({ children }) => {
  const [currentUser, setCurrentUser] = useState(null);

  return (
    <CurrentUserStateContext.Provider value={[currentUser, setCurrentUser]}>
      {children}
    </CurrentUserStateContext.Provider>
  );
};
