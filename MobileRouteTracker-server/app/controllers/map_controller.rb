class MapController < ApplicationController

  def index
    @account = Account.where(:accountnr => params[:accountnr], :pass => params[:pass]).first
    @positions = @account.positions
    @last_position = @positions.last
  end

end
