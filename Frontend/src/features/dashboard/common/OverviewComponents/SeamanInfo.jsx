import {
  Search,
  Plus,
  ChevronDown,
  ChevronRight,
  ExternalLink,
} from "lucide-react";
import blueprint from "../../../../assets/common/blueprint.png";
const SeamanInfo = () => {
  return (
    <>
      <div className="grid grid-cols-1 lg:grid-cols-3 gap-2">
        <div className="bg-[#1f3c88] mx-2 text-white p-2 rounded-xl shadow-md">
          <h3 className="font-semibold mb-2">Materials 🛠️</h3>
          <ul className="text-lg space-y-4 pt-2">
            <li>Engine Parts : 100/100</li>
            <li>Environmental Machineries : 10/10</li>
            <li>Fluid Machineries : 500/500</li>
            <li>Electric Spares : 1000/1000</li>
            <li>Etc : 200/200</li>
          </ul>
        </div>

        <div className="bg-[#1f3c88] text-white p-2 rounded-xl shadow-md">
          <h3 className="font-semibold mb-2">Design</h3>
          <img src={blueprint} alt="blueprint" />
        </div>

        <div className="bg-[#1f3c88] text-white p-2 rounded-xl shadow-md">
          <h3 className="font-semibold mb-2">Certificates</h3>
          <ul className="text-lg space-y-4 pt-2">
            <li>• SOLAS ( Safety Of Life At Sea )</li>
            <li>
              • STCW ( Standards Of Training, Certification, and Watchkeeping )
            </li>
            <li>• ISM Code ( International Safety Management Code )</li>
            <li>• MARPOL ( Marine Pollution )</li>
          </ul>
        </div>
      </div>
    </>
  );
};
export default SeamanInfo;
