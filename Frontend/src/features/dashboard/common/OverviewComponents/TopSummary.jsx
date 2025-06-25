const TopSummary = () => {
  return (
    <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-6">
    <div className="bg-slate-800 text-white p-6 rounded-xl border-l-4 shadow-md">
          <div className="text-sm text-gray-300 mb-2">TODAY</div>
          <div className="text-2xl font-bold">23 JUNE 2015</div>
        </div>
        <div className="bg-slate-800 text-white p-6 rounded-xl shadow-md">
          <div className="text-sm text-gray-300 mb-2">
            TOTAL SHIPS ASSIGNED TODAY
          </div>
          <div className="text-2xl font-bold">24/35</div>
        </div>
        <div className="bg-slate-800 text-white p-6 rounded-xl shadow-md">
          <div className="text-sm text-gray-300 mb-2">
            TOTAL SEAMEN ON DUTY TODAY
          </div>
          <div className="text-2xl font-bold">150/257</div>
        </div>
  </div>
  );
};

export default TopSummary;
