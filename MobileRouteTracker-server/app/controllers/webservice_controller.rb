class WebserviceController < ApplicationController

  def index 

  end

  def position
    @account = Account.where(:accountnr => params[:accountnr], :pass => params[:pass]).first
    @position = @account.positions.build
    @position.latitude = params[:latitude]
    @position.longitude = params[:longitude]
    @position.altitude = params[:altitude]
    @position.accuracy = params[:accuracy]
    @position.bearing = params[:bearing]
    @position.time = params[:time]
    @position.save
    render :json => @position
  end

  def positions
    @account = Account.where(:accountnr => params[:accountnr], :pass => params[:pass]).first
    @positions = @account.positions
    render :json => @positions
  end

end
