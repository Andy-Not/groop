import { useLocalStorage } from "../hooks/useLocalStorage";
import { Navigate } from "react-router-dom";

const PrivateRouteLogin = ({ children }) => {
  const [jwt, setJwt] = useLocalStorage("", "jwt");
  return jwt ? children : <Navigate to={"/login"} />;
};
export default PrivateRouteLogin;
